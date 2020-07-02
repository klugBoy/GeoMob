package com.example.corona.geomob.data.Repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.corona.geomob.R
import com.example.corona.geomob.data.DAOs.*
import com.example.corona.geomob.data.Domaines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.concurrent.Executors


@Database(entities = [
    (Pays::class),
    (Historique::class),
    (Personnalite::class),
    (Image::class),
    (Tweet::class),
    (Video::class),
    (Ressource::class)
], version = 5)
abstract class SqlLiteDateBase:RoomDatabase() {

    abstract fun getPaysDao() :PaysDAO
    abstract fun getHistoriqueDao() :HistoriqueDAO
    abstract fun getPersonnaliteDAO() :PersonnaliteDAO
    abstract fun getImageDao() :ImageDAO
    abstract fun getTweetDAO() :TweetDAO
    abstract fun getVideoDAO() :VideoDAO
    abstract fun getRessourceDAO() :RessourceDAO

    companion object {

        private var INSTANCE: SqlLiteDateBase? = null
        private val LOCK = Any()

        @Synchronized
        fun getInstance(context: Context?): SqlLiteDateBase? {
            if (INSTANCE == null) {
                INSTANCE = buildDataBase(context!!)
            }
            return INSTANCE
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SqlLiteDateBase::class.java,
            "geomob.db"
        ).addCallback(object:Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                var id1 : Int? = null
                var id5: Int? = null
                var id2: Int? = null
                var id3: Int? = null
                var id4: Int? = null
                CoroutineScope(IO).launch {
                    getInstance(context)?.getPaysDao()?.addMultiplePays(*SqlLiteDateBase.populateData()!!)
                    id1 = getInstance(context)?.getPaysDao()?.getIdPays("South Korea")
                    id2 = getInstance(context)?.getPaysDao()?.getIdPays("Germany")
                    id3 = getInstance(context)?.getPaysDao()?.getIdPays("United Kingdom")
                    id4 = getInstance(context)?.getPaysDao()?.getIdPays("United States")
                    id5 = getInstance(context)?.getPaysDao()?.getIdPays("Russia")
                    getInstance(context)?.getPersonnaliteDAO()?.addMultiplePersonnalite(*populateKoreanPersons(id1!!)!!,*populateGermanPersons(id2!!)!!,*populateEnglishPersons(id3!!)!!,
                        *populateAmericanPersons(id4!!)!!,*populateRussianPersons(id5!!)!!)
                    getInstance(context)?.getRessourceDAO()?.addMultipleRessource(*addKoreanResources(id1!!)!!,*addGermanResources(id2!!)!!,
                        *addEnglishResources(id3!!)!!,*addAmericanResources(id4!!)!!,*addRussianResources(id5!!)!!)
                    getInstance(context)?.getHistoriqueDao()?.addMultipleHistorique(*addKoreanHistory(id1!!)!!,*addGermanHistory(id2!!)!!,
                        *addEnglishHistory(id3!!)!!,*addAmericanHistory(id4!!)!!,*addRussiaHistory(id5!!)!!)
                    getInstance(context)?.getImageDao()?.addMultipleImage(*addKoreaImages(id1!!)!!,*addGermanImages(id2!!)!!,*addEnglishImages(id3!!)!!,
                        *addAmericanImages(id4!!)!!,*addRussianImages(id5!!)!!)
                    getInstance(context)?.getVideoDAO()?.addMultipleVideo(*addKoreanVideos(id1!!)!!,*addGermanVideos(id2!!)!!,*addEnglishVideos(id3!!)!!,
                        *addAmericanVideos(id4!!)!!,*addRussianVideos(id5!!)!!)
                }
            } }).build()
    fun populateData(): Array<Pays>? {
    return arrayOf<Pays>(
    Pays("South Korea",
    "South Korea is a country in East Asia, constituting the southern part of the Korean Peninsula and sharing a land border with North Korea."
    , 100_363,51_709_098, R.drawable.ic_south_korea.toString(),"south_korea.ogg"),
    Pays("Germany",
    "Germany is a country in Central and Western Europe.It borders Denmark to the north, Poland and the Czech Republic to the east, Austria and Switzerland to the south, and France, Luxembourg, Belgium, and the Netherlands to the west."
    , 357_022,83_166_711,R.drawable.ic_germany.toString(),"germany.ogg"),
    Pays("United Kingdom",
    "United Kingdom is a sovereign country located off the north\u00ADwestern coast of the European mainland. The United Kingdom includes the island of Great Britain, the north\u00ADeastern part of the island of Ireland, and many smaller islands.Northern Ireland shares a land border with the Republic of Ireland. Otherwise, the United Kingdom is surrounded by the Atlantic Ocean, with the North Sea to the east, the English Channel to the south and the Celtic Sea to the southwest."
    , 242_495,67_886_004,R.drawable.ic_united_kingdom.toString(),"united_kingdom.ogg"),
    Pays("United States",
    "United States is a country mostly located in central North America, between Canada and Mexico. It consists of 50 states, a federal district, five major self-governing territories, and various possessions."
    , 9_833_520,328_239_523,R.drawable.ic_united_states.toString(),"united_states.ogg"),
    Pays("Russia",
    "Russia is a transcontinental country located in Eastern Europe and Northern Asia."
    , 17_098_246,146_748_590,R.drawable.ic_russia.toString(),"russia.ogg")
    ) }
    fun populateEnglishPersons(countryID:Int):Array<Personnalite>?{
    return arrayOf<Personnalite>(
    Personnalite("Monarch","Alexandra","Elizabeth",countryID),
    Personnalite("Prime Minister","Boris","Johnson",countryID)
    )
    }


    fun populateAmericanPersons(countryID:Int):Array<Personnalite>?{
    return arrayOf<Personnalite>(
    Personnalite("President","Trump","Donald",countryID),
    Personnalite("Vice President","Pence","Mike",countryID),
    Personnalite("House Speaker","Pelosi","Nancy",countryID),
    Personnalite("Chief Justice","Roberts","John",countryID)
    )
    }

    fun populateGermanPersons(countryID:Int):Array<Personnalite>?{
    return arrayOf<Personnalite>(
    Personnalite("President","Steinmeier","Frank-Walter",countryID),
    Personnalite("Chancellor","Merkel","Angela",countryID)
    )
    }

    fun populateRussianPersons(countryID:Int):Array<Personnalite>?{
    return arrayOf<Personnalite>(
    Personnalite("President","Putin","Vladimir",countryID),
    Personnalite("Prime Minister","Mishustin","Mikhail",countryID),
    Personnalite("Speaker of the Federation Council","Matviyenko","Valentina",countryID)
    )
    }

    fun populateKoreanPersons(countryID:Int):Array<Personnalite>?{
    return arrayOf<Personnalite>(
    Personnalite("President","Jae-in","Moon",countryID),
    Personnalite("Prime Minister","Sye-kyun","Chung",countryID),
    Personnalite("Speaker of the National Assembly","Byeong-seug","Park",countryID)
    )
    }
        fun addEnglishResources(countryID:Int):Array<Ressource>?{
            return arrayOf<Ressource>(
                Ressource("coal",countryID),
                Ressource("petroleum",countryID),
                Ressource("natural gas",countryID),
                Ressource("iron ore",countryID),
                Ressource("lead",countryID),
                Ressource("zinc",countryID),
                Ressource("gold",countryID)
            )
        }
        fun addAmericanResources(countryID:Int):Array<Ressource>?{
            return arrayOf<Ressource>(
                Ressource("natural gas",countryID),
                Ressource("uranium",countryID),
                Ressource("petroleum",countryID),
                Ressource("gold",countryID),
                Ressource("silver",countryID),
                Ressource("mercury",countryID)
            )
        }
        fun addGermanResources(countryID:Int):Array<Ressource>?{
            return arrayOf<Ressource>(
                Ressource("coal",countryID),
                Ressource("lignite",countryID),
                Ressource("natural gas",countryID),
                Ressource("iron ore",countryID),
                Ressource("copper",countryID),
                Ressource("nickel",countryID)
            )
        }
        fun addRussianResources(countryID:Int):Array<Ressource>?{
            return arrayOf<Ressource>(
                Ressource("natural gas",countryID),
                Ressource("oil",countryID),
                        Ressource("timber",countryID)
            )
        }
        fun addKoreanResources(countryID:Int):Array<Ressource>?{
            return arrayOf<Ressource>(
                Ressource("coal",countryID),
            Ressource("tungsten",countryID),
                Ressource("graphite",countryID),
                Ressource("molybdenum",countryID),
                Ressource("hydropower potential",countryID)
            )
        }

        fun addAmericanHistory(countryID:Int):Array<Historique>?{
            return arrayOf<Historique>(
                Historique("04/07/1776","Declaration of Independence","The Declaration of Independence happened in New England. The Declaration of Independence is when they signed the document showing we are free from Great Britain. This event was really important to the United States because that did not only mean that we were our own country, but also we got freedom.",countryID)
                ,Historique("01/01/1861","Civil War","The Civil War went on in mostly the South of the United States. It went on from 1861-1865. The Civil War was the first time that the United States were disunited ever since they joined against the British in 1776. The Southern states tried to introduce a new dollar that was pretty much worthless because the country had no gold to back it up. The slaves were Freed, but there was really no change in attitudes of most people towards them.",countryID)
                ,Historique("28/08/1963","Martin Luther King Jr. \"I Have a Dream\" Speech","Martin Luther King Jr. gave his \"I Have a Dream\" speech in Washington. His speech was about how it didn't matter if you were colored or not colored, people are going to be different, look different, and act differently than anyone else would. If it were not for Martin Luther King Jr. for all I know there could still be a lot of discrimination. Colored couldn't be friends with the non colored, colored people couln't go to the same school or go to the bathroom in the same room as non colored.",countryID)
                , Historique("11/09/2001","9/11","9/11 took place in New York, and a little of Pennslyvania. What had happened was Afghanistan sent some people to terrorize the U.S. well of course they did as told. They killed the pilots on their plane ride then crashed the plane. They also shot mistles at the twin towers adventually knocking them down killing many people inlcuding themselves. This event changed everyone in the U.S.A. life. Security changed, buildings, and everything you could think of. This moment would never be forgotten.",countryID)
            )
        }

        fun addGermanHistory(countryID:Int):Array<Historique>?{
            return arrayOf<Historique>(
                Historique("08/05/1945","Germany's surrender in WW2","Adolf Hitler, the Nazi leader, had committed suicide on 30 April during the Battle of Berlin and Germany's surrender was authorised by his successor, Reichspräsident Karl Dönitz in the 8th of MAY 1945.",countryID)
                , Historique("09/11/1989","The fall of Berlin's wall","On November 9, 1989, as the Cold War began to thaw across Eastern Europe, the spokesman for East Berlin's Communist Party announced a change in his city's relations with the West. Starting at midnight that day, he said, citizens of the GDR were free to cross the country's borders.",countryID)

            )
        }

        fun addRussiaHistory(countryID:Int):Array<Historique>?{
            return arrayOf<Historique>(
                Historique("08/03/1917","Bolshevik Revolution","Czar Nicholas abdicates; Bolsheviks, led by Vladimir Ilyich Lenin, take control; Russian Soviet Socialist Republic established; capital moves to Moscow.",countryID)
            ,Historique("28/12/1922","The creation of The Soviet Union"," a conference of plenipotentiary delegations from the Russian SFSR, the Transcaucasian SFSR, the Ukrainian SSR and the Byelorussian SSR approved the Treaty on the Creation of the USSR and the Declaration of the Creation of the USSR, forming the Union of Soviet Socialist Republics.",countryID)
            , Historique("26/12/1991","The dissolution of the Soviet Union","The dissolution of the Soviet Union was the process of internal disintegration within the Union of Soviet Socialist Republics (USSR), which began in the late 1980s with growing unrest in the various constituent republics, and ended on December 26, 1991, when the Supreme Soviet voted the USSR itself out of existence.",countryID)
            , Historique("26/03/2014","The annexation of Crimea","The Crimean Peninsula, north of the Black Sea in Europe, was annexed by the Russian Federation between February and March 2014 and since then has been administered as two Russian federal subjects—the Republic of Crimea and the federal city of Sevastopol.",countryID)
            )
        }
        fun addEnglishHistory(countryID:Int):Array<Historique>?{
            return arrayOf<Historique>(
                Historique("1066","WILLIAM THE CONQUEROR DEFEATS HAROLD AT THE BATTLE OF HASTINGS","Arguably the most famous date in English history, most people can link the year of 1066 with the Battle of Hastings. Whether or not King Harold really was killed by an arrow in the eye, England was transformed by the events on the battlefield in East Sussex that day. William's victory at Hastings earned him the nickname 'the Conqueror' - he was crowned by Christmas and Norman influence swept across the country.",countryID)
                , Historique("1455","WARS OF THE ROSES BEGINS","Less than one hundred years after the Black Death, England was again uprooted by dramatic upheaval - this time man-made. The Wars of the Roses, fought between the two powerful houses of York and Lancaster, pitted families against each other. After the removal of King Henry VI, the country faced three decades of rebellion and plotting. This was only ended when Henry Tudor defeated Richard III at the Battle of Bosworth in 1485, which marked the beginning of the age of the Tudors.",countryID)
                , Historique("1815","THE BATTLE OF WATERLOO","The Battle of Waterloo was the final clash after years of war between European nations and French emperor Napoleon Bonaparte. The allied forces led by the Duke of Wellington - who descibred the battle as 'a damned close-run thing' - defeated Napoleon's imperial amibitions. This led to peace in Europe for years to come and helped to end centuries of conflict between England and France.",countryID)
            )
        }

        fun addKoreanHistory(countryID:Int):Array<Historique>?{
            return arrayOf<Historique>(
                Historique("15/08/1948","The establishment of the republic of korea","The First Republic was founded on 15 August 1948 after the transfer from the United States Army Military Government that governed South Korea since the end of Japanese rule in 1945, becoming the first independent capitalist republican government in Korea.",countryID)
            )}
        fun addAmericanImages(countryID: Int):Array<Image>?{
            return arrayOf<Image>(
                Image("https://upload.wikimedia.org/wikipedia/commons/4/4f/Statueofliberty.JPG",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/a/ac/OneWorldTradeCenter.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/a/a3/Texas_medical_center.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/d/da/Panorama_of_United_States_Supreme_Court_Building_at_Dusk.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/f/f5/Photos_NewYork1_032.jpg",countryID)
            )
        }

        fun addGermanImages(countryID: Int):Array<Image>?{
            return arrayOf<Image>(
                Image("https://upload.wikimedia.org/wikipedia/commons/d/d7/Skyline_Frankfurt_am_Main_2015.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/a/ad/Striezelmarkt_2009_00950.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/3/32/Stadtbild_K%C3%B6ln_%28Zuschnitt%29.jpg",countryID)
            )}
        fun addRussianImages(countryID: Int):Array<Image>?{
            return arrayOf<Image>(
                Image("https://upload.wikimedia.org/wikipedia/commons/a/a0/Petersburg-square.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/5/58/City_Duma_Building_%28Rostov-on-Don%292.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/8/81/E-burg_asv2019-05_img45_view_from_VysotSky.jpg",countryID)
            )}
        fun addKoreaImages(countryID: Int):Array<Image>?{
            return arrayOf<Image>(
                Image("https://upload.wikimedia.org/wikipedia/commons/a/a5/Marine_City_skyline_in_Busan%2C_South_Korea.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/e/ea/National_Assembly_Building_of_the_Republic_of_Korea.png",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/3/3e/Daeungjeon_at_Bulguksa-Gyeongju-Korea-01.jpg",countryID)
            )}
        fun addEnglishImages(countryID: Int):Array<Image>?{
            return arrayOf<Image>(
                Image("https://upload.wikimedia.org/wikipedia/commons/6/6d/City_of_London_skyline_from_London_City_Hall_-_Sept_2015_-_Crop_Aligned.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/4/4c/30_St_Mary_Axe_from_Leadenhall_Street.jpg",countryID),
                Image("https://upload.wikimedia.org/wikipedia/commons/9/9a/Central_Birmingham_Skyline_%286305750228%29.jpg",countryID)
            )}

        fun addRussianVideos(countryID: Int):Array<Video>?{
            return arrayOf<Video>(
                Video("moscowandstpetersburg","The majesty of Moscow and St Petersburg",countryID)
                ,Video("russiantour","Kalinka Malinka Russian tour",countryID)
            )
        }
        fun addEnglishVideos(countryID: Int):Array<Video>?{
            return arrayOf<Video>(
                Video("travellondoninaminute","Travel London in a Minute - Aerial Drone Video",countryID),
                Video("londonengland","London - England",countryID)
            )
        }
        fun addKoreanVideos(countryID: Int):Array<Video>?{
            return arrayOf<Video>(
                Video("korea","TRAVELLING _ SOUTH KOREA in one minute",countryID),
                Video("travelseoulinaflash","Travel Seoul in a Flash - Hyperlapse & Aerial Videos",countryID)
            )
        }
        fun addAmericanVideos(countryID: Int):Array<Video>?{
            return arrayOf<Video>(
                Video("videousa","Cedar City, Utah, United States",countryID),
                Video("bestofusa","Best of USA",countryID)
            )
        }
        fun addGermanVideos(countryID: Int):Array<Video>?{
            return arrayOf<Video>(
                Video("trieste","TRIESTE in 2 minutes",countryID),
                Video("berlin","2 minute tours_ Berlin",countryID)
            )
        }

}

}