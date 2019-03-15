package tp1;
// PENSEZ A INDIQUER PAR DES COMMENTAIRES LES MODIFICATIONS APPORTEES A CE SQUELETTE AU FUR
// ET A MESURE DE L'EVOLUTION DU CODE DEMANDEE DANS LE TP.

/**
 * Les objets instances de la classe Usine represente une usine avec deux ateliers.
 * Une instance d'Usine possede un stock de pieces a transformer ainsi qu'un stock
 * de pieces finies initialement vide. Chacun des deux ateliers transforme la moitie
 * des unites du stock a transformer. 
 * La methode fonctionner() fait travailler successivement les deux ateliers et affiche
 * l'etat des stocks a la fin des travaux.
 */
class Usine{
	/**
	 * Stock de pieces a transformer
	 */
//	Stock stockDepart = new Stock("de depart", 100000000);
	static Stock stockDepart = new Stock("de depart", 10, 10);
   
   /**
	 * Stock de pieces a transformer
	 */
 //	Stock stockDepart = new Stock("de depart", 100000000);
   static  Stock stockMiddle = new Stock("de middle", 0, 1);
  
  
    /**
     * Stock de pieces transformees
     */
   static Stock stockFin = new Stock("d'arrivee", 0, 10);
    /**
     * Ateliers de transformation
     */
//    Atelier atelier1 = new Atelier(stockDepart, stockFin, 50000000);
//    Atelier atelier2 = new Atelier(stockDepart, stockFin, 50000000);
    Atelier atelier1 = new Atelier(stockDepart, stockFin, 5);
    Atelier atelier2 = new Atelier(stockDepart, stockFin, 5);
    
    /**
     * Effectuer le travail de l'usine
     * Utilise successivement chaque atelier pour transformer une piece et affiche
     * l'evolution de l'etat des stocks.
     */
    public void fonctionner() {
//    		atelier1.run(); // this is was method travailler in class Atelier which was rename to run
//    		atelier2.run(); // this is was method travailler in class Atelier which was rename to run
    		atelier1.start();
    		atelier2.start();
    		try {
				atelier1.join();
				atelier2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		stockDepart.afficher();
    		stockFin.afficher();
    }
    
    /**
     * Point d'entree pour l'ensemble du TP.
     * @param args Non utilise
     */
    public static void main(String[] args) {

//    	Usine _usine = new Usine();
//    	_usine.fonctionner();
    	
    	// 3.1 making the first two plants
    	Thread firstWork = new Thread(new Atelier(stockDepart, stockMiddle, 5));
    	Thread firs = new Thread(new Atelier(stockDepart, stockMiddle, 5));
    	Thread secondWork = new Thread(new Atelier(stockMiddle, stockFin, 5));
    	
    	// 3.3 making the third plant
    	Thread thirdWork = new Thread(new Atelier(stockMiddle, stockFin, 5));
    	
    	firstWork.start();
    	firs.start();
    	secondWork.start();
    	thirdWork.start();
    	
    	try {
			firstWork.join();
			firs.join();
			secondWork.join();
			thirdWork.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	stockDepart.afficher();
    	stockMiddle.afficher();
    	stockFin.afficher();
    	
    	
    }
}
