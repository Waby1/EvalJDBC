import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MuscuJdbc {

public static Scanner scanner = new Scanner(System.in);
	// testing git push
public static void ajouter() {
		
		System.out.println( " Saisir le nom du nouvel adherent ");
	    String reponseNom = scanner.nextLine();
		
	    
	    boolean saisieKo = true;
	    Integer reponseAge = new Integer(0);
		
	    while(saisieKo)
		{
		    System.out.println( " Saisir l'age ");
		    try {
		    	reponseAge = Integer.valueOf(scanner.nextLine());
		    	saisieKo = false;
		    }catch(NumberFormatException e){
		    	System.out.println("Saisir un entier svp !!!");
		    	
		    }
		}
	    
	    saisieKo = true;
	    Integer reponsePoids = new Integer(0);
		
	    while(saisieKo)
		{
		    System.out.println( " Saisir le poids ");
		    try {
		    	reponsePoids = Integer.valueOf(scanner.nextLine());
		    	saisieKo = false;
		    }catch(NumberFormatException e){
		    	System.out.println("Saisir un entier svp !!!");
		    	
		    }
		}
	    
	    
	    
	    
	    saisieKo = true;
	    Double reponseTaille = new Double(0);
		
	    while(saisieKo)
		{
		    System.out.println( " Saisir la taille ");
		    try {
		    	reponseTaille = Double.valueOf(scanner.nextLine());
		    	saisieKo = false;
		    }catch(NumberFormatException e){
		    	System.out.println("Saisir un Double svp !!!");
		    	
		    }
		}
		
		
		Connection connectDbMuscu = ConnectionDbMuscu.getConnection();
	
		
		String sqlInsert = "INSERT INTO \"MuscuEval\" (\"nameCustom\", \"ageCustom\", \"weightCustom\", \"heightCustom\") VALUES(?,?,?,?) RETURNING  \"idCustom\"";
		PreparedStatement statementInsert; 
		try {
			
			statementInsert = connectDbMuscu.prepareStatement(sqlInsert);
			
			statementInsert.setString(1, reponseNom);
			statementInsert.setInt(2, reponseAge);
			statementInsert.setInt(3, reponsePoids);
			statementInsert.setDouble(4, reponseTaille);
			ResultSet resid = statementInsert.executeQuery();
			while(resid.next()) {
				System.out.println("Total iD crées : "+resid.getLong("idCustom"));
			}
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
	}
	
public static void lister() {
	
	String sql = "SELECT * FROM \"MuscuEval\"";
	
    try {
    	Connection connectDbMuscu = ConnectionDbMuscu.getConnection();
		PreparedStatement statement = connectDbMuscu.prepareStatement(sql) ;
		ResultSet customSet = statement.executeQuery();
					
		while ( customSet.next()) {
			System.out.println("Num ad : " + "(" + customSet.getLong("idCustom")+ ")" + " " + " - Nom : " +customSet.getString("nameCustom") + " " + " - Age : " + customSet.getInt("ageCustom") + " " + "- Poids : " + customSet.getDouble("weightCustom") + " " + "- Taille : " + customSet.getDouble("heightCustom"));
		}
		
	} 
		catch (SQLException e) {
	
		e.printStackTrace();
		
	}
 
	
}	

public static void maj() {

boolean saisieKo = true;	    
Integer reponseId = new Integer(0) ;

while(saisieKo)
{
    System.out.println( " Saisir le numéro d'adhérent ");
    try {
    	reponseId = Integer.valueOf(scanner.nextLine());
    	saisieKo = false;
    }catch(NumberFormatException e){
    	System.out.println("Saisir un entier svp !!!");
    	
    }
}

System.out.println( " Saisir le nom ");
String reponseNom = scanner.nextLine();


saisieKo = true;
Integer reponseAge = new Integer(0);

while(saisieKo)
{
    System.out.println( " Saisir l'age ");
    try {
    	reponseAge = Integer.valueOf(scanner.nextLine());
    	saisieKo = false;
    }catch(NumberFormatException e){
    	System.out.println("Saisir un entier svp !!!");
    	
    }
}

saisieKo = true;
Integer reponsePoids = new Integer(0);

while(saisieKo)
{
    System.out.println( " Saisir le poids ");
    try {
    	reponsePoids = Integer.valueOf(scanner.nextLine());
    	saisieKo = false;
    }catch(NumberFormatException e){
    	System.out.println("Saisir un entier svp !!!");
    	
    }
}




saisieKo = true;
Double reponseTaille = new Double(0);

while(saisieKo)
{
    System.out.println( " Saisir la taille ");
    try {
    	reponseTaille = Double.valueOf(scanner.nextLine());
    	saisieKo = false;
    }catch(NumberFormatException e){
    	System.out.println("Saisir un Double svp !!!");
    	
    }
}


Connection connectDbMuscu = ConnectionDbMuscu.getConnection(); 

String sqlUpdate = "UPDATE public.\"MuscuEval\" SET  \"nameCustom\"=?, \"ageCustom\"=?, \"weightCustom\"=?, \"heightCustom\"=?	WHERE \"idCustom\"=?;";
PreparedStatement statementUpdate;
try {
	statementUpdate = connectDbMuscu.prepareStatement(sqlUpdate);
	statementUpdate.setString(1, reponseNom);
	statementUpdate.setInt(2, reponseAge);
	statementUpdate.setInt(3, reponsePoids);
	statementUpdate.setDouble(4, reponseTaille);
	statementUpdate.setInt(5, reponseId);
	statementUpdate.executeUpdate();
	
} catch (SQLException e) {
	
	e.printStackTrace();
}

}
	
public static void supp() {
	
	
	boolean saisieKo = true;
    Integer reponseId = new Integer(0);
	
    while(saisieKo)
	{
	System.out.println( " Saisir le numero id de l'adherent ");
   
    
	    try {
	    	reponseId = Integer.valueOf(scanner.nextLine());
	    	saisieKo = false;
	    }catch(NumberFormatException e){
	    	System.out.println("Saisir un Double svp !!!");
	    	
	    }
	}
    
    
	Connection connectDbMuscu = ConnectionDbMuscu.getConnection();
	
	String sqlDelete = "DELETE FROM \"MuscuEval\" WHERE \"idCustom\" = ?";
	PreparedStatement statementDelete;
	try {
		statementDelete = connectDbMuscu.prepareStatement(sqlDelete);
		statementDelete.setInt(1, reponseId);			
		statementDelete.executeUpdate();
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}

public static void record() {



	String sql = "SELECT * FROM \"Record\"";
	sql = "SELECT * FROM \"Record\" AS rec, \"MuscuEval\" AS muscu WHERE rec.\"idCustom\" = muscu.\"idCustom\"";
    try {
    	Connection connectDbMuscu = ConnectionDbMuscu.getConnection();
		PreparedStatement statement = connectDbMuscu.prepareStatement(sql) ;
		ResultSet customSet = statement.executeQuery();
					
		while ( customSet.next()) {
			System.out.println( " - Name : " +customSet.getString("nameCustom") + " " + " - Max Bench : " + customSet.getInt("maxBench") + " " + "- Max Squat : " + customSet.getDouble("maxSquat") + " " + "- Date Perf : " + customSet.getInt("datePerf") );
		}
		
	} 
		catch (SQLException e) {
	
		e.printStackTrace();
		
	}
 
	
}	
	
public static void main(String[] args) {
	
	 Connection connectDbMuscu = ConnectionDbMuscu.getConnection();
		 
		 
		 Integer adh;
		 
		 boolean exit = false;
		 
		 do {
	     System.out.println(" ****** Bienvenue sur LARSON ****** ");
		 System.out.println("      ... Menu Principal ... ");
		 System.out.println("Tappez 1) - Ajoutez un nouvel adherent -  ");
		 System.out.println("Tappez 2) - Consultez la liste des adherents -  ");
		 System.out.println("Tappez 3) - Mettre à jour un adherent -");
		 System.out.println("Tappez 4) - Supprimez un adherent -");
		 System.out.println("Tappez 5) - Affichez les Perfs Max des adherents -");
		 System.out.println("Autre touche) Quitter le programme");
		 
		 try {
		 adh = Integer.valueOf(scanner.nextLine()) ;
		 
		 switch(adh) {
		 	
		    case 1:
		 		MuscuJdbc.ajouter();
			break;
		 	
		 	case 2:
		 		MuscuJdbc.lister();
		 	break;
		 	
		 	case 3:
		 		MuscuJdbc.maj();
		 	break;
		 	
		 	case 4: 
		 		MuscuJdbc.supp();
		 	break;
		 	
		 	case 5:
		 		MuscuJdbc.record();
		 	default:
		 		exit = true;
		 	}
		 }catch(NumberFormatException e) {
			 exit = true;
		 }
	 
		 
		 }while(!exit);
		 
		 return;	

	}

}
