package mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import utils.AppContextSingleton;
import codel.Address;
import codel.Contact;
import codel.ContactGroup;
import codel.PhoneNumber;
import dao.IDAOContact;
import dao.IDAOContactGroup;

@ManagedBean(name="contactGenerator")
public class ContactGenerator implements Serializable {
	private static final long serialVersionUID = 1L;
	private String noms[] = {"Jean-Michel Bilou","Albert Bon","Jean-Paul Thibault","Jean-FranÃ§ois Darras","Thierry Smith","Eric Lhote","Philippe Gosse","Eric Gallois","Jean-Claude Masse","Vincent Andres","Jean-Pierre Lesieur","LoÃ¯c Collot","Alain Huchet","Yves Chatelain","JoÃ«l Vial","Roger Jouan","Gilbert Faure","Emmanuel Bourbon","Julien Sorel","Alexis Vallon","FranÃ§ois Trouillet","RenÃ© Diot","Marc Payet","Cyril Defrance","Vincent Hamon","Cyril Manceau","StÃ©phane Parra","HervÃ© Boisseau","Roland Lhote","Georges Blaise","Laurent Jardin","Jean-Michel Montagne","Michel Cabrera","Christophe Danet","Bruno Molinier","Christian Michaut","Xavier Pinson","Gilles Ripoll","Joseph Marquant","Guy Piot","Bernard Bernard","Patrick Legrand","Jonathan Kremer","ClÃ©ment Christophe","JoÃ«l Lacombe","Antoine Escoffier","Maxime Marco","Francis Moreno","Arnaud Ferrero","Lionel Charvet","Sylvain Lagrange","Charles Denis","Henri Bonhomme","Thierry Pere","Denis Humbert","Claude Lang","Fabrice Lebel","Jean Huart","Guy Barbe","Francis Pinot","Albert Landry","SÃ©bastien Gutierrez","Emile Cloarec","Alexis Pere","Sylvain Page","HervÃ© Juge","Robert Baron","Jean-Paul Maignan","Jean-Michel Vergnaud","Jean-FranÃ§ois Chevrier","Benjamin Bois","Michel Thiriet","Georges Hoffmann","Henri Vuillemin","Jean Bardon","Lionel Mougel","Raymond Grolleau","Sylvain Ducret","Xavier Guillaume","Patrick Rouault","Franck Cazaux","Christian Huynh","Julien Mallard","SÃ©bastien Corbin","Nicolas Jarry","MickaÃ«l Jeanne","Lucien Cosson","Jean-Claude Benoit","Maurice Brousse","Cyril Bru","Michel Le calvez","Yannick Rougier","Benjamin Laffitte","Paul Arnaud","Bernard Philippe","Pierre Dedieu","Francis Grolleau","GrÃ©gory Bricout","Dominique Clavier","Claude Courant","Louise Voisin","Jade Baudoin","Lou Rouanet","Heloise Fayolle","Juliette Corre","Solene Pons","Capucine Ripoll","Laura Henrion","Alexia Guilbaud","Rose Ragot","Alicia Pere","Lena Barrois","Marie Garel","Faustine Lafarge","Laura Wendling","Fanny Dupont","Leonie Gallo","Anais Renou","Laurine Pichot","Clara Allaire","Mathilde Berthelot","Ines Guilbert","Emma Ferre","Emilie De sousa","Celia Moro","Melissa Chevrier","Laura Varin","Luna Alvarez","Camille Cardoso","Elisa Anton","Fanny Danel","Oceane Lefrancois","Marine Levasseur","Lou Boisson","Elsa Beck","Angelina Rouyer","Laurine Girault","Louane Bouquet","Amandine Carles","Alice Pinto","Sarah Combe","Morgane Giry","Manon Lesueur","Maeva Ricci","Clarisse Bataille","Jeanne Besson","Jade Morales","Clarisse Rousselet","Amelie Picaud","Lena Dumon","Pauline Point","Cloe Duhem","Cassandra Jamet","Emma Bois","Margaux Richer","Louise Le corre","Melina Frey","Lisa Minet","Melina Pillon","Gabrielle Auvray","Gabrielle Beal","Sarah Ribot","Coline Thibaud","Celia Chopin","Leonie Marchetti","Eva Giordano","Charlotte Lamarque","Yasmine Ortega","Capucine Leclerc","Lina Lavaud","Sarah Guesdon","Adele Roze","Louna Faure","Leonie Lebas","Pauline Roche","Ines Guillemain","Lena Bureau","Manon Lemonnier","Morgane Pannetier","Laurine Cartier","Victoria Simoes","Fanny Fonteneau","Camille Le grand","Noemie Gaudin","Lisa Ballet","Alexia Bellet","Clementine Perraud","Melina Fradet","Laura Tardieu","Maelys Bisson","Elsa Baillet","Mathilde Guignard","Lena Charron","Lina Renier","Cassandra Ly","Salome Wagner","Juliette Douay","Adele Bonnin","Maeva Joubert","Solene Lozano"};
	private String villes[] = {"Lanpy","Faux","Queral","Puylmargues","Lmosque","Quegnont","Sasque","Trosque","Mongneux","Auran","Monveux","Rner","Sivosse","Monmille","Aboduse","Lluse","Peylosque","BarÃ©ovy","Niruse","Chamchoirname","Peyraux","Aunalle","Obec","Monrnosse","Oipargues","Ipasque","Rosse","Convrail","Peyrnanches","Cabont","Dullur","Mininail","Londargues","Igont","Tois","Tabeux","Sidan","Suse","Oifer","Quernasque","Lonvy","Vauran","Oulanmosque","Oivibac","Doman","Canrer","RÃ©obec","Capille","Bripour","Malonront","Quetalm","Reson","Tadac","Choitran","Botrois","Mondont","Rnail","Mongnuse","Evrargues","Dognon","Vibame","Dille","Oibac","Mignois","Lurac","Belmaux","TarÃ©ogord","Pame","Remifois","Lonllour","Rnaux","Lanront","Font","Durosse","Oilal","Tresail","AudulmÃ´ne","Condosque","Tarour","Tevac","Tont","Oulille","Dubollanches","Begreux","Elandenc","Dusille","Ruire","Econbur","Peyver","Monlmac","Vaulangnes","ConvÃ´ne","Atregont","Tresalm","Marois","Vivibec","Lmont","Balman","Macanlmasque","Bent"};
	private String rues[] = {"rue de Val-sous-suif","rue de Eaunoire","rue de Rocdur","rue de Torlac","rue de Rocheroyale","rue de Rocbois","rue de Eauclair","rue de Mar-sur-ours","rue de Castel-de-suif","rue de Malplaine","rue de Francfort","rue de Rudedune","rue de Terrelongue","rue de Maleville","rue de Grandmoulin","rue de Folpont","rue de RieÃ©strange","rue de Maleplaine","rue de Portmort","rue de TertreÃ©cume","rue de Aprerieu","rue de Beaupont","rue de Salpont","rue de Grandemuraille","rue de Talpic","rue de Moulinsaint","rue de Mortepierre","rue de Champfeu","rue de Mont-en-brume","rue de Malpic","rue de Blanport","rue de ChÃ¢teau-fier","rue de Roncefranche","rue de Apresherbes","rue de Maisonclair","rue de ChÃ¢teau-en-Ã©cume","rue de Portembrum","rue de Montembrum","rue de Richepic","rue de Marvieux","rue de AiguefiÃ¨re","rue de Taleville","rue de Froidfosse","rue de Vieillegrange","rue de Roncefranche","rue de Fondpierre","rue de Rieu-de-laine","rue de Portblanc","rue de Loc-Ã -cerf","rue de Blanport","rue de Ormroux","rue de Bourgloup","rue de Vieilletour","rue de Pic-sur-mine","rue de Fierforge","rue de Vertmoulin","rue de Hautegrange","rue de Apremotte","rue de Mar-sur-Ã©cume","rue de Vignevaux","rue de Port-croc","rue de Mornebutte","rue de Pontmirail","rue de Aprefosse","rue de Rudebutte","rue de Tertre-de-cerf","rue de Ormclair","rue de Champlin","rue de Rieu-sur-pluie","rue de Vignerouge","rue de Clairedune","rue de Dompierre","rue de Pontvieux","rue de Montvieux","rue de Froidterre","rue de Champ-sur-loup","rue de Noblesherbes","rue de Marsaint","rue de Loc-Ã -embrum","rue de Villeclair","rue de Lai-sur-pÃ¨re","rue de Francrieu","rue de Rocours","rue de Pont-de-puit","rue de Vieilroc","rue de Pontcourbe","rue de ChÃ¢teau-clair","rue de Granderoche","rue de Marblanc","rue de Maleplaine","rue de Picmine","rue de Fondtour","rue de Domseaux","rue de Taleplaine","rue de Vignerousse","rue de Dombutte","rue de Tertre-de-sel","rue de Mortforge","rue de Roquevieille","rue de Maleroche"};
	private String groupes[] = {"Indie Dyke","Symptom Plumber","Satiated Reaper","Decently Tribute","Blonde Conglomerate","Social Painter","Headache Scenario","Minor Hedgehog","Religious Leash","Armed Pickle","Swift Quartet","Shocking Screw","Flogging Hip","Explosive Gyro","Sixteen Of The Neutron Revenge","Anatomy Of Impotent","Viper Kiwi","Liberal Randy And The Burden","Open Pardon","Coyote Of Blank","Simple Syndicate Of The Confined Minion","Official Trilogy And The Orifice","Ailing Galore","Battered Dweeb And The Step-dad","Regular Mumbles","Swinger Insect","Fogotten Sex","Latin Cool","Pink Kiss","Striker Of The Washer","Uncanny War","Finally Provider","Wanted Torpedo And The Game Kickoff","More Of The Cycle","Balance Of The Compressed Lullaby","Lazy Condition And The Idiotic Cheap","Hand For Bacon","Driving Hyphen And The Enraged Health","Lead Twister","Letting Anything"};
	
	public String gen50Contacts(){
		try {
			this.generateContacts(50);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "50 contacts gÃ©nÃ©rÃ©s alÃ©atoirement ajoutÃ©s avec succes!", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (Exception e){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la gÃ©nÃ©ration des contacts.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		
		return "contactGenerator";
	}
	
	public String genWithGetterAndSetter(){
		try {
			IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
			dao.addContact((Contact)AppContextSingleton.getContext().getBean("Donnee_test_1"));
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact gÃ©nÃ©rÃ© par getter/setter ajoutÃ© avec succes!", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (Exception e){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la gÃ©nÃ©ration du contact.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "contactGenerator";
	}
	
	public String genWithConstructor(){
		try {
			IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
			dao.addContact((Contact)AppContextSingleton.getContext().getBean("Donnee_test_2"));
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact gÃ©nÃ©rÃ© par constructeur ajoutÃ© avec succes!", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (Exception e){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la gÃ©nÃ©ration du contact.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "contactGenerator";
	}
	
	public void generateContacts(long num){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		Random r = new Random();
		
		generateGroups(num/10);
		
		// Jean-Michel AdministrateurSysteme
		Contact c = new Contact();
		c.setFirstName("Jean-Michel");
		c.setLastName("Administrateur Systeme");
		c.setEmail("jeanmicheladministrateursysteme@gmail.com");
		c.setAdd(new Address((r.nextInt(500) + 1) + " " + rues[r.nextInt(rues.length)], villes[r.nextInt(villes.length)], (r.nextInt(99) + 1) + ""+ r.nextInt(1000), "FR"));
		if(r.nextBoolean()){
			if(r.nextBoolean()){
				c.addProfile(new PhoneNumber("mobile", "06" + r.nextInt(100000000)));
			} else {
				c.addProfile(new PhoneNumber("mobile", "07" + r.nextInt(100000000)));
			}
		}	
		if(r.nextBoolean()){
			c.addProfile(new PhoneNumber("office", "01" + r.nextInt(100000000)));
		}
		if(r.nextBoolean()){
			c.addProfile(new PhoneNumber("home", "01" + r.nextInt(100000000)));
		}		
		dao.addContact(c);
		generateAssocGroupContacts(c.getId(), r.nextInt(3));
		// fin de l'easter egg
		
		for(int i=0; i<num; i++){
			c = new Contact();
			c.setFirstName(noms[r.nextInt(noms.length)].split(" ")[0]);
			c.setLastName(noms[r.nextInt(noms.length)].split(" ")[1]);
			c.setEmail(c.getFirstName().toLowerCase() + "." + c.getLastName().toLowerCase() + "@gmail.com");
			c.setAdd(new Address((r.nextInt(500) + 1) + " " + rues[r.nextInt(rues.length)], villes[r.nextInt(villes.length)], (r.nextInt(99) + 1) + ""+ r.nextInt(1000), "FR"));
			
			if(r.nextBoolean()){
				if(r.nextBoolean()){
					c.addProfile(new PhoneNumber("mobile", "06" + r.nextInt(100000000)));
				} else {
					c.addProfile(new PhoneNumber("mobile", "07" + r.nextInt(100000000)));
				}
			}
			
			if(r.nextBoolean()){
				c.addProfile(new PhoneNumber("office", "01" + r.nextInt(100000000)));
			}
			
			if(r.nextBoolean()){
				c.addProfile(new PhoneNumber("home", "01" + r.nextInt(100000000)));
			}
			
			dao.addContact(c);
			generateAssocGroupContacts(c.getId(), r.nextInt(3));
		}
	}

	private void generateAssocGroupContacts(long id, long num) {
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		Random r = new Random();
		List<ContactGroup> list = dao.getAllContactGroups();
		for(int i=0; i<num; i++){
			dao.addContactToGroup(id, list.get(r.nextInt(list.size())).getGroupName());
		}
	}

	private void generateGroups(long num) {
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		Random r = new Random();
		for(int i=0; i<num; i++){
			ContactGroup cg = new ContactGroup();
			cg.setGroupName(groupes[r.nextInt(groupes.length)]);
			if(dao.getContactGroupByName(cg.getGroupName()) == null){
				dao.addContactGroup(cg);
			}
		}
	}
}