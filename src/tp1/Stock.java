package tp1;
// PENSEZ A INDIQUER PAR DES COMMENTAIRES LES MODIFICATIONS APPORTEES A CE SQUELETTE AU FUR
// ET A MESURE DE L'EVOLUTION DU CODE DEMANDEE DANS LE TP.

/**
 * Les objets instances de la classe Stock representent un ensemble de pieces, 
 * empilees les unes sur les autres. Du fait de la disposition en piles, il n'est
 * pas possible que deux operateurs saisissent deux pieces au meme moment. 
 *
 */
class Stock {
	/**
	 * Nombre de pieces dans la pile
	 */
    private int nbPieces;
    
    /**
     *  Maximum number of stock
     */
    private int nbMaxPieces;
    /**
     * Le nom du stock
     */
    private String nom;

    /**
     * Creer un nouvel objet instance de stock
     * @param nom Le nom du nouveau stock
     * @param nbPieces Le nombre de pieces initial
     */
    public Stock(String nom, int nbPieces, int nbMaxPieces) {
        this.nbPieces = nbPieces;
        this.nbMaxPieces = nbMaxPieces;
        this.nom = nom;		
    }

    /**
     * Poser une piece sur le haut de la pile de pieces
     */
    public synchronized void stocker() {

    	// 3.4 
    	while(nbPieces == nbMaxPieces) {
    		try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	   nbPieces++; 
    	
    	   // 3.1 to 3.3
    	//   notify(); // we use a notify in this case bcos at the moment, we have only one plant that 
    	   // as been created to put stocks into stockMiddle
    	   //System.out.println(Thread.currentThread().getName() + " current thread " + nbPieces);
    	   
    	   // 3.4 we used notifyAll in this case bcos we have two plants which is used to stock in stockMiddle
    	   notifyAll();
    }

    /**
     * Saisir une piece sur le haut de la pile de pieces
     */
    public synchronized void destocker() {
    	// 3.2 removing the if loop and seeing the negative sign, and putting it back for question 3.3
    	// in this case we use "if " condition bcos there is only one plant (thread -secondWork) which can destock from
    	// stockMiddle, and while it is waiting, it sends notification (notify()) to the thread (stockDebut) (saying i am waiting).
//    	if(nbPieces == 0) {
//    		try {
//    			wait();
//				
//			} catch (InterruptedException e) {e.printStackTrace();}
//    	}
//        nbPieces--;
//        System.out.println(Thread.currentThread().getName() + " Stock Number " + nbPieces);
        
    	// 3.3  We use while condition in this case bcos there are two plants ( thread : secondWork and thirdWork)
    	//  which can destock from stockMiddle, and the both plants can not destock at the same time.
        while(nbPieces == 0) {
    		try {
    			wait();
				
			} catch (InterruptedException e) {e.printStackTrace();}
    	}
        nbPieces--;       
        
        // 3.4
        notifyAll(); // this is used here bcos we have two plants which are destocking from stockMiddle
        System.out.println(Thread.currentThread().getName() + " Stock Number " + nbPieces + " Max Stock Number " + nbMaxPieces);
    }

    /**
     * Affiche l'etat de l'objet stock
     */
    public synchronized void afficher() {
        System.out.println("Le stock " + nom + " contient " + nbPieces + " piece(s), " + nbMaxPieces + " Mix piece(s).");
    }

    /** 
     * Methode d'auto-test pour la classe Stock
     * @param args Non utilise
     */
    static public void main(String[] args) {
    	Stock _stock = new Stock("cookie Dough", 10, 10);
    	_stock.afficher();//to get the stock 
    	_stock.stocker();
    	_stock.destocker();
    	_stock.afficher();


    }
}
