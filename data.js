db.clients.insertOne({
    "nom":"Jean",
    "prenom":"Marcus",
    "email":"jean@gmail.com",
    "mdp":"azerty",
    "telephone":"0341254805"    
});

db.circuits.insertOne({
    "nom":"Faune et Flore",
    "description":{
        "titre":"Escapade sud malgache",
        "info":"Le Sud de Madagascar promet les découvertes les plus riches et les plus surprenantes en matière de paysage, de flore et de faune."
    },
    "photos":[
        "https://www.voyagemadagascar.com/cdn/mg-public/detours_madagascar_ifaty-MAX-w1000h600.jpg",
        "https://www.voyagemadagascar.com/cdn/mg-public/dsc_0417-MAX-w1000h600.jpg",
        "https://www.voyagemadagascar.com/cdn/mg-public/detours_madagascar_ifaty1-MAX-w1000h600.jpg"
    ],
    "tags":["Randonné","Voyage avec des enfants","Aventure, exporation"],
    "itineraires":{
        "titre":"Antananarivo - Tulear",
        "nbrJours":7,
        "trajets":[
            {
                "lieu_depart":"Antananarivo",
                "lieu_arrivee":"Tulear > Ifaty",
                "duree":"1h30m - 1h30m ",
                "distance":"935km - 45km",
                "transport":"avion - voiture",
                "description":"Décollage à destination de Tuléar. Transfert en voiture pour Ifaty, village de pêcheurs situé à 45km au nord de Tuléar."
            },
            {
                "lieu_depart":"Séjour libre à Ifaty",
                "description":"Ifaty, Mangily, Madiorano et Ambolimailaka sont de petits villages qui font face au lagon majestueux de Ranobe à l’eau turquoise, réputé pour sa barrière de corail. Ils font ici office de stations balnéaires."
            },
            {
                "lieu_depart":"Ifaty",
                "lieu_arrivee":"Tulear - Ranohirana",
                "duree":"45m - 4h",
                "distance":"45km - 290km",
                "transport":"voiture",
                "description":"Départ pour une journée de route, sur la RN7 en passant dans la ville de Tuléar nous remontons vers le village de Ranohira. Sur la route nous verrons des nombreux tombeaux bien décoré des ethnies des Antandroy et Mahafaly."
            },
            {
                "lieu_depart":"Découverte du Parc National de l'Isalo",
                "lieu_arrivee":"Ranohirana",
                "duree":"4h",
                "distance":"20km",
                "tranport":"A pieds",
                "description":"Une belle journée de découverte à pied dans le massif ruiniforme de l'Isalo. Le Parc National de l'Isalo a été créé en 1962, et est classé dans la catégories des parc exceptionnels. Étendu sur près de 100 km du Nord au Sud, il est unique à Madagascar de par sa formation géomorphologique datant du Jurassique"
            },
            {
                "lieu_depart":"Ranohirana",
                "lieu_arrivee":"Ambalavao - Fianarantsoa",
                "duree":"4h30m - 1h30m",
                "distance":"210km - 60km",
                "transport":"voiture",
                "description":"Toujours l’immense plateau de l’Horombe jusqu’à Ihosy. Nous quittons le sud aride pour commencer à monter les contreforts des Hautes terres. Continuation de notre route jusqu’à Ambalavao. Continuation vers Fianarantsoa."
            },
            {
                "lieu_depart":"Fianarantsoa",
                "lieu_arrivee":"Antsirabe",
                "duree":"6h40m",
                "distance":"250km",
                "transport":"voiture",
                "description":"Départ pour Antsirabe situé à 240km vers le Nord. Quelques arrêts en cours de route pour explorer toujours les abords des vestiges sur la route. Arrivée à Antsirabe en fin de journée, installation à l’hôtel."
            },
            {
                "lieu_depart":"Antsirabe",
                "lieu_arrivee":"Antananarivo",
                "duree":"4h",
                "distance":"180km",
                "transport":"voiture",
                "description":"Petit tour de la ville et visite chez les artisans locaux (confection de miniatures de vélos et de voitures, travail de la corne de zébu…) puis route pour Ambatolampy. Les rizières et les villages caractéristiques des Hautes Terres dessinent le paysage. Continuation dans l’après-midi vers Antananarivo."
            }
        ]
    },
    "disponibilite":{
        "date_debut":"20/05/2023",
        "date_fin":"11/06/2023",
        "disponible":10,
        "prix":"2500000",
        "statut":true
    },
    "reservation":[{}]
});

