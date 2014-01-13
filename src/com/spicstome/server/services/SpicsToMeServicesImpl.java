package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.hibernate.HibernateUtil;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Log;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.User;

public class SpicsToMeServicesImpl extends RemoteServiceServlet implements SpicsToMeServices {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	public UserDTO getUser(String login, String password) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).add(Restrictions.eq("password",password)).list();
	    session.getTransaction().commit();
	    
	    if (users.isEmpty())
	    	return null;
	    else {
	    	UserDTO userDTO = createUserDTO(users.get(0));	    	
	    	getThreadLocalRequest().getSession().setAttribute("currentUser", userDTO);	    
	    	return userDTO;
	    }
	}

	@Override
	public UserDTO getCurrentUser() {
		return (UserDTO)getThreadLocalRequest().getSession().getAttribute("currentUser");
	}
	
	@Override
	public boolean disconnectCurrentUser() {
		getThreadLocalRequest().getSession().setAttribute("currentUser", null);
		return (getCurrentUser()==null);
	}
	
	@Override
	public Long saveImage(ImageDTO imageDTO) {
		Image image = new Image(imageDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(image);
	    session.getTransaction().commit();
	    return image.getId();
	}
	
	@Override
	public Long saveFolder(FolderDTO folderDTO) {
		Folder folder = new Folder(folderDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(folder);
	    session.getTransaction().commit();
	    return folder.getId();
	}
	
	@Override
	public Long saveArticle(ArticleDTO articleDTO) {
		
		ImageDTO imageArticle = articleDTO.getImage();
		Long id = saveImage(imageArticle);
		imageArticle.setId(id);
		
		Article article = new Article(articleDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
	    session.beginTransaction();
	    session.save(article);
	    session.getTransaction().commit();
	    return article.getId();
	}
	
	@Override
	public Long saveAlbum(AlbumDTO albumDTO) {
		Album album = new Album(albumDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(album);
	    session.getTransaction().commit();
	    return album.getId();
	}	

	@Override
	public Long saveStudent(StudentDTO studentDTO) {
		
		// saving a default album
		ImageDTO imageFolder = new ImageDTO((long) -1, "all.png");
		Long idImageFolder = saveImage(imageFolder);
		imageFolder.setId(idImageFolder);
		
		FolderDTO folder = new FolderDTO((long) -1, "Tout", 0, null, imageFolder, new HashSet<PecsDTO>());
		Long idFolder = saveFolder(folder);
		folder.setId(idFolder);
		
		AlbumDTO album = new AlbumDTO((long) -1, folder);
		Long idAlbum = saveAlbum(album);
		album.setId(idAlbum);
		studentDTO.setAlbum(album);
		
		Long idImageUser = saveImage(studentDTO.getImage());
		studentDTO.getImage().setId(idImageUser);
		
		Student student = new Student(studentDTO);		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(student);
	    session.getTransaction().commit();
	    
	    return student.getId();
	}	

	@Override
	public AlbumDTO getAlbum(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Album> list = session.createCriteria(Album.class).add(Restrictions.eq("id",id)).list();
		session.getTransaction().commit();
		
		if(list.size()>0)
		{
			return createAlbumDTO(list.get(0));
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public List<AlbumDTO> getReferentAlbums() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Album> list = session.createCriteria(Album.class).list();
		session.getTransaction().commit();
		
		List<AlbumDTO> listAlbumDTO=new ArrayList<>();

		if(!list.isEmpty())
		{ 
			for(int i=0;i<list.size();i++)
			{
				listAlbumDTO.add(createAlbumDTO(list.get(i)));
			}
		}

		return listAlbumDTO;
	

	}
	
	private List<FolderDTO> GetFoldersFolder(FolderDTO folder)
	{
		List<FolderDTO> res = new ArrayList<FolderDTO>();
		
		res.add(folder);

		for(PecsDTO p:folder.getContent())
		{
			if(p instanceof FolderDTO)
			{
				res.addAll(GetFoldersFolder((FolderDTO)p));
				
			}
		}	
		
		return res;
	}
	

	@Override
	public List<FolderDTO> getFoldersAlbum(long id) {
		
		AlbumDTO albumDTO = getAlbum(id);
		if(albumDTO.getFolder()!=null)
		{
			return GetFoldersFolder(albumDTO.getFolder());
		}
		else
		{
			return null;
		}
	}

	@Override
	public StudentDTO getAlbumOwner(long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    @SuppressWarnings("unchecked")    
	    List<Album> list = session.createCriteria(Album.class).add(Restrictions.eq("id",id)).list();
	    if(list.size()==0) return null;
	    @SuppressWarnings("unchecked") 
		List<Student> users = session.createCriteria(Student.class).add(Restrictions.eq("album",list.get(0))).list();
	    session.getTransaction().commit();
	    
	    if(users.size()>0)
	    	return createStudentDTO(users.get(0));
	    else
	    	return null;

	}
	
	
	/* ***********************************************
	 * FONCTION DE TRANSTYPAGE
	 * 
	 * *************************************************/
	
	private ImageDTO createImageDTO(Image image) {
		return new ImageDTO(image.getId(), image.getFilename());
	}
	
	private AlbumDTO createAlbumDTO(Album album)
	{
		return new AlbumDTO(album.getId(),createFolderDTO(album.getFolder()));
	}
	
	private FolderDTO createFolderDTO(Folder folder)
	{
		return new FolderDTO(folder.getId(),folder.getName(),folder.getOrder(),null,createImageDTO(folder.getImage()),createListPecsDTO(folder.getContent()));
	}
	
	private ArticleDTO createArticleDTO(Article article)
	{
		return new ArticleDTO(article.getId(),article.getName(),article.getOrder(),null,createImageDTO(article.getImage()),null);
	}
	
	private LogDTO createLogDTO(Log log)
	{
		return new LogDTO(log.getId(),createStudentDTO(log.getStudent()),
				log.getEmailRecipient(),log.getDate(),createListArticleDTO(log.getArticles()));
	}
	
	private Set<LogDTO> createListLogDTO(Set<Log> list)
	{
		Set<LogDTO> listDTO=new HashSet<>();
		
		for(Log log:list){		
			listDTO.add(createLogDTO(log));
		}

		return listDTO;
	}
	
	private Set<ArticleDTO> createListArticleDTO(Set<Article> list)
	{
		Set<ArticleDTO> listDTO=new HashSet<>();
		
		for(Article article:list){	
			listDTO.add(createArticleDTO(article));
		}

		return listDTO;
	}
	
	private Set<PecsDTO> createListPecsDTO(Set<Pecs> list)
	{
		Set<PecsDTO> listDTO=new HashSet<>();
		
		for(Pecs p:list)
		{
			if(p instanceof Article)
				listDTO.add(createArticleDTO((Article)p));
			if(p instanceof Folder)
				listDTO.add(createFolderDTO((Folder)p));
		}

		return listDTO;
	}
	
	

	private UserDTO createUserDTO(User user) {
		return new UserDTO(user.getId(), user.getSubscriptionDate(), user.getFirstName(), 
				user.getName(), user.getEmail(), user.getLogin(), user.getPassword(), createImageDTO(user.getImage()));
	}

	private StudentDTO createStudentDTO(Student student)
	{
		return new StudentDTO(student.getId(),
				student.getSubscriptionDate(),
				student.getFirstName(),
				student.getName(),	 
				student.getEmail(), 
				student.getLogin(), 
				student.getPassword(), 
				createImageDTO(student.getImage()),
				createAlbumDTO(student.getAlbum()),
				createListLogDTO(student.getLogs()),null,null);
			
		// null null => le mec qui en a eu marre mais c'est la liste des referent et des teacher donc methode
		// pour transcrire teacher , list de teacher ...
	}



	
	
	
}
