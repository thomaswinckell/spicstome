package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.shared.Adjective;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Log;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Subject;
import com.spicstome.client.shared.Teacher;
import com.spicstome.client.shared.User;
import com.spicstome.client.shared.Verb;

public class Transtypage {
	

	public static ImageDTO createImageDTO(Image image) {
		return new ImageDTO(image.getId(), image.getFilename());
	}
	
	public static AlbumDTO createAlbumDTO(Album album)
	{
		
		return new AlbumDTO(album.getId(),createFolderDTO(album.getFolder(),null));
	}
	
	public static FolderDTO createFolderDTO(Folder folder,FolderDTO parentDTO)
	{
		if (folder == null)
			return null;
		
		FolderDTO folderDTO = new FolderDTO(folder.getId(),folder.getName(),folder.getOrder(),parentDTO,createImageDTO(folder.getImage()),null);
		//on donne à createListPecsDTO le folderDTO créer ici , pour éviter les boucle infini
		folderDTO.setContent(createListPecsDTO(folder.getContent(),folderDTO));
		return folderDTO;
	}
	
	public static VerbDTO createVerbDTO(Verb verb,FolderDTO parentDTO)
	{
		return new VerbDTO(verb.getId(),verb.getName(),verb.getOrder(),parentDTO,createImageDTO(verb.getImage()),
				createListLogDTO(verb.getLogs()),
				verb.getFavorite(),
				verb.getGroup(),
				verb.getType(),
				verb.getIrregular1(),
				verb.getIrregular2(),
				verb.getIrregular3(),
				verb.getIrregular4(),
				verb.getIrregular5(),
				verb.getIrregular6());
	}
	
	public static AdjectiveDTO createAdjectiveDTO(Adjective adjective,FolderDTO parentDTO)
	{
		return new AdjectiveDTO(adjective.getId(),adjective.getName(),adjective.getOrder(),parentDTO,createImageDTO(adjective.getImage()),
				createListLogDTO(adjective.getLogs()),
				adjective.getFavorite(),	
				adjective.getMatching1(),
				adjective.getMatching2(),
				adjective.getMatching3(),
				adjective.getMatching4());
	}
	
	public static SubjectDTO createSubjectDTO(Subject subject,FolderDTO parentDTO)
	{
		return new SubjectDTO(subject.getId(),subject.getName(),subject.getOrder(),parentDTO,createImageDTO(subject.getImage()),
				createListLogDTO(subject.getLogs()),subject.getFavorite(),subject.getGender(),subject.getNature(),subject.getNumber());
	}
	public static LogDTO createLogDTO(Log log)
	{
		return new LogDTO(log.getId(),createStudentDTO(log.getStudent()),
				log.getEmailRecipient(),log.getDate(),createListArticleDTO(log.getArticles()));
	}
	
	public static Set<LogDTO> createListLogDTO(Set<Log> list)
	{
		Set<LogDTO> listDTO=new HashSet<>();
		
		for(Log log:list){		
			listDTO.add(createLogDTO(log));
		}

		return listDTO;
	}
	
	public static Set<ArticleDTO> createListArticleDTO(Set<Article> list)
	{
		Set<ArticleDTO> listDTO=new HashSet<>();
		
		for(Article article:list){	
			if(article instanceof Subject)
				listDTO.add(createSubjectDTO((Subject)article,null));
			else if(article instanceof Verb)
				listDTO.add(createVerbDTO((Verb)article,null));
		}

		return listDTO;
	}
	
	public static ArrayList<PecsDTO> createListPecsDTO(Set<Pecs> list,FolderDTO parent)
	{
		ArrayList<PecsDTO> listDTO=new ArrayList<PecsDTO>();
		
		for(Pecs p:list)
		{
			
			if(p instanceof Folder)
				listDTO.add(createFolderDTO((Folder)p,parent));
			else
			{
				if(p instanceof Subject)
					listDTO.add(createSubjectDTO((Subject)p,parent));
				if(p instanceof Verb)
					listDTO.add(createVerbDTO((Verb)p,parent));
				if(p instanceof Adjective)
					listDTO.add(createAdjectiveDTO((Adjective)p,parent));
			}
				
				
					
		}

		return listDTO;
	}


	public static UserDTO createUserDTO(User user) {
		/*return new UserDTO(user.getId(), user.getSubscriptionDate(), user.getFirstName(), 
				user.getName(), user.getEmail(), user.getLogin(), user.getPassword(), createImageDTO(user.getImage()));*/
		if (user instanceof Student)
			return createStudentDTO((Student) user);
		else if (user instanceof Teacher)
			return createTeacherDTO((Teacher) user);
		else if (user instanceof Referent)
			return createReferentDTO((Referent) user);
		else
			return null;
	}

	public static ReferentDTO createReferentDTO(Referent referent) {
		Set<Student> students = referent.getStudents();
		ArrayList<StudentDTO> studentDTOs = new ArrayList<StudentDTO>(students != null ? students.size() : 0);
		if (students != null) {
			for (Student student : students) {
				studentDTOs.add(createStudentDTO(student));
			}
		}
		
		return new ReferentDTO(referent.getId(), referent.getSubscriptionDate(), referent.getFirstName(), 
				referent.getName(), referent.getEmail(), referent.getLogin(), referent.getPassword(), createImageDTO(referent.getImage()), 
				studentDTOs);
	}
	
	public static TeacherDTO createTeacherDTO(Teacher teacher) {
		
		Set<Student> students = teacher.getStudents();
		ArrayList<StudentDTO> studentDTOs = new ArrayList<StudentDTO>(students != null ? students.size() : 0);
		if (students != null) {
			for (Student student : students) {
				studentDTOs.add(createStudentDTO(student));
			}
		}
		
		return new TeacherDTO(teacher.getId(), teacher.getSubscriptionDate(), teacher.getFirstName(), 
				teacher.getName(), teacher.getEmail(), teacher.getLogin(), teacher.getPassword(), createImageDTO(teacher.getImage()), 
				studentDTOs);
	}
	
	public static StudentDTO createStudentDTO(Student student) {
				
		Set<Log> logs = student.getLogs();
		Set<LogDTO> logDTOs = new HashSet<LogDTO>(logs != null ? logs.size() : 0);
		if (logs != null) {
			for (Log log : logs) {
				logDTOs.add(createLogDTO(log));
			}
		}
		
		return new StudentDTO(student.getId(), student.getSubscriptionDate(), student.getFirstName(), 
				student.getName(), student.getEmail(), student.getLogin(), student.getPassword(), createImageDTO(student.getImage()), 
				createAlbumDTO(student.getAlbum()), logDTOs);
	}
}
