package fr.unicaen.info.users.a21606807.ventesimmobilires.db;

import android.provider.BaseColumns;

public class VentesImmobilieresContract {

    public static  final String DB_NAME = "Ventes_Immobilieres";

    public static class ProprieteEntry implements BaseColumns {
        public static final String TABLE_NAME = "Propriete";
        public static final String COL_ID = "_id";
        public static final String COL_TITRE = "titre";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_PIECES = "nb_pieces";
        public static final String COL_CARACTERISTIQUES = "caracteristiques";
        public static final String COL_PRIX = "prix";
        public static final String COL_VILLE = "ville";
        public static final String COL_ID_VENDEUR = "id_vendeur";
        public static final String COL_IMAGES = "images";
        public static final String COL_DATE = "date";
    }

    public static class VendeurEntry implements BaseColumns {
        public static final String TABLE_NAME = "Vendeur";
        public static final String COL_ID = "_id";
        public static final String COL_NOM = "nom";
        public static final String COL_PRENOM = "prenom";
        public static final String COL_EMAIL = "email";
        public static final String COL_TEL = "telephone";
    }
}
