********To Change Inputs for any given question:************
Go to the answerQuestions method in GetInfo and follow comments there to change inputs for any given question.
If the user wishes to find out data about any particular region, please run the program with one of the following inputs:


_________________________________________________________________________________________________________________

******To Run the Program**********
Run GetInfo and follow the instructions prompted on the screen.
_________________________________________________________________________________________________________________

Overview:

The code is separated into two classes—Country and GetInfo.

Country Class:

The country class is used to store information about a country including its related region, its name, and its url.

GetInfo Class:

GetInfo has the main method. The main method first connects to the home page of the cia world factbook and determines the name, region, link associated with each country and assigns all this
information to a map. The method getAnswers is where the inputs for each individual question can be manipulated. getAnswers is
called from the main method with two inputs—the map and a number—where the map corresponds the the data for each country and
the number corresponds to the question number.

Additionally, I store all my data as regions, so if the user wishes
_________________________________________________________________________________________________________________

Questions:
_________________________________________________________________________________________________________________

Question 1:

Solution: [Argentina, Chile, Colombia, Ecuador, Peru]

This algorithm takes in the region as well as the keyword and searches the page for it.
If the page contains the keyword, the country is added to a list which is then printed to the user.
_________________________________________________________________________________________________________________
Question 2:

[Algeria, Angola, Aruba, Australia, Azerbaijan, BosniaandHerzegovina, Brazil, BurkinaFaso, Burma,
Burundi, CaboVerde, Cameroon, CaymanIslands, CentralAfricanRepublic, Chile, China, Comoros,
CongoDemocraticRepublicofthe, CookIslands, Cuba, Curacao, Djibouti, Dominica, EquatorialGuinea,
EuropeanUnion, FrenchPolynesia, Ghana, Grenada, Guinea-Bissau, Honduras, HongKong, Jordan, KoreaNorth,
Kosovo, Liberia, Libya, Macau, Malaysia, MarshallIslands, Mauritania, MicronesiaFederatedStatesof,
Mozambique, Nauru, NewZealand, Niue, NorthernMarianaIslands, Pakistan, Panama, PapuaNewGuinea, Paraguay,
Philippines, PuertoRico, SaintKittsandNevis, Samoa, SaoTomeandPrincipe, Senegal, Singapore, Slovenia,
SolomonIslands, Somalia, SouthSudan, Suriname, Syria, Tajikistan, Timor-Leste, Togo, Tokelau, Tunisia,
Turkey, Turkmenistan, Tuvalu, UnitedStates, Uzbekistan, Venezuela, Vietnam, Zimbabwe]

The above countries all have a star on their flag. The algorithm looks at the flag description in the html of
each given country using regex. If there is a star in the description, it then can be concluded that this country
has a star. We omit any countries where there is a star under the “note:” section of the flag description. This algorithm
can be used for any keyword under the flag description category.
_________________________________________________________________________________________________________________
Question 3:

HolySee(VaticanCity)

The algorithm looks at all the countries in the specified region and finds its population using regex and adds it to a map
where the key is the population and the value is the country. After all the countries are designated a population,
the country with the ‘least key’—i.e. Least population—is returned to the user. Assuming that country and region
are interchangeable here.
_________________________________________________________________________________________________________________
Question 4:

[Armenia, Azerbaijan, Bahrain, GazaStrip, Georgia, Israel, Jordan, Kuwait, Lebanon, Qatar, UnitedArabEmirates, WestBank]
[Brunei, HongKong, KoreaSouth, Macau, ParacelIslands, Singapore, SpratlyIslands, Taiwan]
[Bhutan, BritishIndianOceanTerritory, Maldives, SriLanka]

The algorithm takes in a region and returns all of the places within the region that has a total area less than Pennsylvania’s
total area (119,280 km’s). Since the region of Asia is split up into four distinct regions (Middle East, Central Asia, South
Asia, and East & Southeast Asia), the algorithm is run four different times with each of these regions as inputs to determine
all the countries in asia that have total area less than Pennsylvania.
_________________________________________________________________________________________________________________
Question 5:

1863 : International	Committee	of	the	Red	Cross	(ICRC)
1865 : International	Telecommunication	Union	(ITU)
1874 : Universal	Postal	Union	(UPU)
1889 : Inter-Parliamentary	Union	(IPU)
1890 : Organization	of	American	States	(OAS)
1894 : International	Olympic	Committee	(IOC)
1899 : Permanent	Court	of	Arbitration	(PCA)
1919 : International	Chamber	of	Commerce	(ICC)
1919 : International	Federation	of	Red	Cross	and	Red	Crescent	Societies	(IFRCS)
1919 : International	Hydrographic	Organization	(IHO)

This algorithm looks through all the international groups and organizations in Appendix B of the cia world factbook.
Using regex, the algorithm determines the html between the words “established” and “aim”. The algorithm then finds a
string of four numbers which corresponds to the year number using regex. Finally once the establishment years for all
applicable groups and organizations are determined, the ten oldest are printed to the user.
_________________________________________________________________________________________________________________
Question 6:

Less than 50%:

[Angola, Australia, Belarus, Belize, Benin, Bermuda, Cameroon, Canada, CentralAfricanRepublic, China, ChristmasIsland,
CongoRepublicofthe, Coted'Ivoire, Czechia, Estonia, Ethiopia, EuropeanUnion, Fiji, Gabon, Germany, Grenada, Guinea-Bissau,
Guyana, Honduras, HongKong, Hungary, KoreaSouth, Latvia, Malawi, Mauritius, Mozambique, Netherlands, NewZealand, NorfolkIsland,
Palau, PapuaNewGuinea, Russia, Singapore, SintMaarten, SouthAfrica, Suriname, Switzerland, Taiwan, Togo, TrinidadandTobago,
Uganda, UnitedStates, Uruguay, Vietnam, World]

Greater than 80%:

[Afghanistan, Algeria, AmericanSamoa, Argentina, Armenia, Azerbaijan, Bangladesh, Burma, Cambodia, Comoros, Croatia, Cuba,
Cyprus, Djibouti, DominicanRepublic, Egypt, FaroeIslands, GambiaThe, GazaStrip, Georgia, Greece, Guam, Guinea, Indonesia,
Iran, Iraq, Jordan, Kenya, Kosovo, Liberia, Libya, Mali, Malta, Mauritania, Mexico, Moldova, Monaco, Morocco, Nepal, Oman,
Pakistan, Panama, Paraguay, Peru, Philippines, PitcairnIslands, Poland, Portugal, PuertoRico, Romania, SaintPierreandMiquelon,
SaudiArabia, Senegal, Serbia, Swaziland, Syria, Tajikistan, Thailand, Timor-Leste, Tunisia, Turkey, Turkmenistan, Tuvalu,
Uzbekistan, Venezuela, WallisandFutuna, Yemen]

This algorithm takes in two additional integer inputs (more and less) which correspond to the italicized numbers in the
given question. The algorithm searches for the “Religions:” section, then determines the religion and percentage by parsing
the data section for religions. The algorithm only takes into account countries which have a % in the religion section.

_________________________________________________________________________________________________________________
Question 7:

[Lesotho, SanMarino, Lesotho, HolySee(VaticanCity), SanMarino]

The algorithm searches through all countries for two distinct characteristics. These characteristics are that the country
must be landlocked and the country has only one bordering country. If a given country satisfies these conditions, it is
added to an ArrayList which is then returned to the user.

_________________________________________________________________________________________________________________
Question 8:
Question: List the 10 countries in the world that have the largest coastlines?

202080.0=Canada
54716.0=Indonesia
44087.0=Greenland
37653.0=Russia
36289.0=Philippines
29751.0=Japan
25760.0=Australia
25148.0=Norway
19924.0=UnitedStates
17968.0=Antarctica


Searches for the occurence of coastlines on each page using regex and finds the associated data for each country. This
information is then inputted into a map, from integer to string where integer is the coastline length in kilometers and
the string is the country. Finally, the last 10 elements of the map are printed to the user—these are the 10 longest coastlines.
_________________________________________________________________________________________________________________
Question 9 (Extra credit 1):

This algorithm finds the population for a given country as well as the electricity consumption for the country.
This is done using regex. Once these figures have been determined, the country and related consumption per capita
is added to a treemap. Finally, the last 10 elements of the treemap are printed to show the 10 countries that have
the highest electricity consumption per capita.

Iceland
Norway
Kuwait
Bahrain
UnitedArabEmirates
_________________________________________________________________________________________________________________