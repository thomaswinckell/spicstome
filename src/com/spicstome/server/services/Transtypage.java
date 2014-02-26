package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.shared.Adjective;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Word;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Log;
import com.spicstome.client.shared.Noun;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Pronoun;
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
		folderDTO.setContent(createListPecsDTO(folder.getContent(),folderDTO));
		return folderDTO;
	}
	
	
	
	public static VerbDTO createVerbDTO(Verb verb,FolderDTO parentDTO)
	{
		return new VerbDTO(verb.getId(),verb.getName(),verb.getOrder(),parentDTO,createImageDTO(verb.getImage()),
				verb.getFavorite(),
				verb.getNegation(),
				verb.getGroup(),
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
				adjective.getFavorite(),	
				adjective.getMatching1(),
				adjective.getMatching2(),
				adjective.getMatching3(),
				adjective.getMatching4());
	}
	public static PronounDTO createPronounDTO(Pronoun pronoun,FolderDTO parentDTO)
	{
		return new PronounDTO(pronoun.getId(),pronoun.getName(),pronoun.getOrder(),parentDTO,createImageDTO(pronoun.getImage()),
				pronoun.getFavorite(),pronoun.getGender(),pronoun.getPerson(),pronoun.getNumber());
	}
	public static NounDTO createNounDTO(Noun noun,FolderDTO parentDTO)
	{
		return new NounDTO(noun.getId(),noun.getName(),noun.getOrder(),parentDTO,createImageDTO(noun.getImage()),
				noun.getFavorite(),noun.getGender(),noun.getNumber(),noun.getUncountable());
	}
	
	public static ArticleDTO createArticleDTO(Article article,FolderDTO parentDTO)
	{
		return new ArticleDTO(article.getId(),article.getName(),article.getOrder(),parentDTO,createImageDTO(article.getImage()),
				article.getFavorite(),article.getGender(),article.getNumber());
	}
	public static LogDTO createLogDTO(Log log,StudentDTO student)
	{
		return new LogDTO(log.getId(),student,
				log.getEmailRecipient(),log.getDate(),log.getMessageLength(),log.getExecutionTime(),log.getActions());
	}
	
	public static ArrayList<LogDTO> createListLogDTO(Set<Log> list,StudentDTO student)
	{
		ArrayList<LogDTO> listDTO=new ArrayList<LogDTO>();
		
		for(Log log:list){		
			listDTO.add(createLogDTO(log,student));
		}

		return listDTO;
	}
	
	public static Set<WordDTO> createListWordDTO(Set<Word> list)
	{
		Set<WordDTO> listDTO=new HashSet<>();
		
		for(Word word:list){	
			if(word instanceof Subject)
			{
				if(word instanceof Noun)
					listDTO.add(createNounDTO((Noun)word,null));
				else if(word instanceof Pronoun)
					listDTO.add(createPronounDTO((Pronoun)word,null));
				else if(word instanceof Article)
					listDTO.add(createArticleDTO((Article)word,null));
			}		
			else if(word instanceof Verb)
				listDTO.add(createVerbDTO((Verb)word,null));
			else if(word instanceof Adjective)
				listDTO.add(createAdjectiveDTO((Adjective)word,null));
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
				{
					if(p instanceof Noun)
						listDTO.add(createNounDTO((Noun)p,parent));
					else if(p instanceof Pronoun)
						listDTO.add(createPronounDTO((Pronoun)p,parent));
					else if(p instanceof Article)
						listDTO.add(createArticleDTO((Article)p,parent));
				}		
				else if(p instanceof Verb)
					listDTO.add(createVerbDTO((Verb)p,parent));
				else if(p instanceof Adjective)
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
				
		StudentDTO studentDTO = new StudentDTO(student.getId(), student.getSubscriptionDate(), student.getFirstName(), 
				student.getName(), student.getEmail(), student.getLogin(), student.getPassword(), createImageDTO(student.getImage()), 
				createAlbumDTO(student.getAlbum()),null);
		
		studentDTO.setLogs(createListLogDTO(student.getLogs(), studentDTO));

		return studentDTO;
	}
}
