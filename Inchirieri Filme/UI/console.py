class Console():

    def __init__(self, comand1, comand2, comand3):
        self.__comand1 = comand1
        self.__comand2 = comand2
        self.__comand3 = comand3

    def Main(self):
        """
            Reads the command that the user gives and returns the command as a string
        """
        print("Type 'x' to exit.")
        print("Choose a menu: Movie, Client, Contracts")
        return input("Option:").strip()

    def Movie_Menu(self):
        """
            Prints the commands for movies and returns the command that the client picked as a string
        """
        print(" ")
        print("---------------------------------------------------------------")
        print(" Write 'back' to go back to the main menu")
        print(" Write 'add' to add a new movie in the list")
        print("Write 'remove' to delete a movie from the list")
        print("Write 'show' if you want to see all the movies in the list")
        print("Write 'search' to search a movie after his name")
        print("Write 'undo' to undo one action in the movie list")
        print("Write 'update' to update the dates of a movie")
        print("Write 'random' to generate random a movie")
        print("----------------------------------------------------------------")

    def Client_Menu(self):
        """
            Prints the commands for client meniu and returns the command that the client picked as a string
        """
        print(" ")
        print("------------------------------------------------------------------")
        print(" Write 'back' to go back to the main menu")
        print(" Write 'add' to add a new client in the list")
        print("Write 'remove' to delete a client from the list")
        print("Write 'show' if you want to see all the clients in the list")
        print("Write 'search' to search a client after his first and last name")
        print("Write 'undo' to undo one action made in the client list")
        print("Write 'update' to update the dates of a client")
        print("Write 'random' to generate random a client")
        print("-------------------------------------------------------------------")

    def Contract_Menu(self):
        """
            Prints the commands for contracts and returns the command that the client picked as a string
        """
        print(" ")
        print("---------------------------------------------------------------")
        print(" Write 'back' to go back to the main menu")
        print(" Write 'add' (a.k.a rent a movie) to add a new contract in the list")
        print("Write 'remove' (a.k.a return a movie) to delete a contract from the list")
        print("Write 'search' to searhc a contract after id")
        print("Write 'show' if you want to see all the contracts in the list")
        print("Write 'show name' to see clients with rented movies sorted after name")
        print("Write 'show movie' to see the latest 10 movies ")
        print("Write 'show rent' to see clients with rented movies sorted after the number of rents")
        print("Write 'top' to see 5 most rented movies")
        print("Write 'first' to see the first 30% clients")
        print("----------------------------------------------------------------")

    def createMovie(self):
        """
            creates a movie
        """
        title = input("Give movie title:")
        genre = input("Give movie type:")
        description = input("Give movie description:")
        year = input("Give movie year:")
        try:
            movie = self.__comand1.createMovie(title, genre, description, year)
            if movie is True:
                print("\nThe movie: ", title, "was added.")
            else:
                print(movie)
        except ValueError as error:
            print(error)

    def randomMovie(self):
        """
        generates random movies
        """
        number = int(input("How many movies you want to generate? "))
        for i in range(0, number):
            movie = self.__comand1.RandomMovie()
            self.__comand1.createMovie(movie.getTitle(), movie.getType(), movie.getDescription(), movie.getYear())

        self.__comand1.showMovieList()

    def removeMovie(self):
        """
            deletes a movie from the list after his id
        """
        title = input("Give movie Title:")
        self.__comand1.deleteMovie(title)
        self.__comand1.showMovieList()

    def searchMovie(self):
        """
        searches a movie after title
        """
        title = input("Title:")
        if self.__comand1.searchMovie(title) is not None:
            for i in self.__comand1.searchMovie(title):
                print(i)

        else:
            print('The movie "', title, '" was not found. ')

    def updateMovies(self):
        """
        updates the dates of a movie after searches it into the movie list
        """
        title = input("Title:")
        if self.__comand1.searchMovie(title) is not None:
            self.__comand1.searchMovie(title)
            newTitle = input("New title:")
            newDescription = input("New description:")
            newType = input("New type:")
            newYear = input("New year:")
            self.__comand1.updateMovie(title, newTitle, newType, newDescription, newYear)
        else:
            print('The movie "', title, '" was not found. ')

    def showMovies(self):
        """
            show the movie lists
        """
        if self.__comand1.showMovieList() is False:
            return False
        else:
            print("The movie list:")
            self.__comand1.showMovieList()

    def undoMovies(self):
        """
            undone's the movie list one time
        """
        self.__comand1.undoMovieList()
        self.__comand1.showMovieList()

    def createClient(self):
        """
        creates a client and stores it in the repository
        """
        name = input("Give last name:")
        surname = input("Give first name :")
        CNP = input("Give NIN:")
        try:
            c = self.__comand2.createClient(name, surname, CNP)
            if c is True:
                available = True
                if available is False:
                    print("Not available.")
                    self.__comand2.deleteClient(CNP)
                else:
                    print("Mr. / Mrs. ", name, " ", surname, "was added.")
            else:
                print(c)
        except ValueError as er:
            print(er)

    def removeClient(self):
        """
        deletes a client
        """
        NIN = input("Give NIN:")
        self.__comand2.returnClient(NIN)
        self.__comand2.deleteClient(NIN)
        self.__comand2.showClientList()

    def randomClient(self):
        """
        generates random clients
        """
        number = int(input("How many clients you want to generate? "))
        for i in range(0,number):
            client = self.__comand2.RandomClient()
            self.__comand2.createClient(client.getName(), client.getSurname(), client.getNIN())


    def searchClient(self):
        """
        searches a client
        """
        name = input("Give last name:")
        surname = input("Give first name :")
        if self.__comand2.searchClient(name, surname) is not None:
            for i in self.__comand2.searchClient(name, surname):
                print(i)
        else:
            print('Mr. / Mrs.', name, ' ', surname, ' was not found. ')


    def undoClient(self):
        """
            undoes the client list once
        """
        self.__comand2.undoClientList()
        self.__comand2.showClientList()

    def updateClients(self):
        """
            udates the dates of a client
        """
        name = input("Give last name of the client you want to modify:")
        surname = input("Give first name of the client you want to modify::")
        if self.__comand2.searchClient(name, surname) is not None:
            for i in self.__comand2.searchClient(name, surname):
                print(i)
            newName = input("Give the new last name :")
            newSurname = input("Give the new first name :")
            newNIN = input("Give the new NIN:")
            self.__comand2.updateClient(name, surname, newName, newSurname, newNIN)
        else:
            print('Mr. / Mrs.', name, ' ', surname, ' was not found. ')

    def createContractInsert(self):
        """
        adds a contract to the list
        """
        title = input("The movie title that the client wants to rent: ")
        NIN = input("The NIN of the client: ")
        if title == "" or NIN =="":
            if title == "":
                print("Id can not be empty")
            if NIN == "":
                print("NIN can not be empty")
        else:
            try:
                if self.__comand2.searchClientNin(NIN) is not None:
                    if self.__comand1.searchMovie(title) is not None:
                        movie = self.__comand1.searchMovie(title)
                        for i in movie:
                            idMovie = i.getId()
                            self.__comand3.createContract(idMovie, title, NIN)
                    else:
                        print("We have no such movie")
                else:
                    print("This client doesn't exist")
            except ValueError as er:
                    print(er)

    def searchContracts(self):
        """
        searches a contract after the id
        """
        id = input("Give the contract id: ")
        if self.__comand3.searchContract(id) is not None:
            for i in self.__comand3.searchContract(id):
                print(i)
        else:
            print("The contract doesn't exist")

    def showContract(self):
        """
            prints the contracts list
        """
        if self.__comand3.showContractList() is False:
            return False
        else:
            print("The contracts list:")
            self.__comand3.showContractList()

    def removeContract(self):
        """
        deletes a contract
        """
        id = int(input("Give ID:"))
        list = self.__comand3.deleteContract(id)
        return list

    def show_rent(self):
        """
        :return: the clients with rented movies sorted after the number of rents
        """
        sorted_list = self.__comand3.sort_nin_selection()
        if sorted_list == []:
            print("The list is empty")
        else:
            rents = []
            nin = sorted_list[0].getNINclient()
            number = 0
            for contract in sorted_list:
                if contract.getNINclient() == nin:
                    number += 1
                else:
                    client = self.__comand2.searchClientNin(nin)
                    name = client.getName()
                    surname = client.getSurname()
                    rent = [name, surname, number]
                    rents.append(rent)
                    nin = contract.getNINclient()
                    number = 1
            client = self.__comand2.searchClientNin(nin)
            name = client.getName()
            surname = client.getSurname()
            rent = [name, surname, number]
            rents.append(rent)

            list = sorted(rents, key=lambda rent: rent[0])
            for i in list:
                print("Client: ", i[0], " ", i[1], "has ", i[2]," movie rented")

    def show_name(self):
        """
        :return: the clients with rented movies sorted after the name of the client
        """
        sorted_list = self.__comand3.sort_nin_selection()
        if sorted_list == []:
            print("The list is empty")
        else:
            rents = []
            nin = sorted_list[0].getNINclient()
            number = 0
            for contract in sorted_list:
                if contract.getNINclient() == nin:
                    number += 1
                else:
                    client = self.__comand2.searchClientNin(nin)
                    name = client.getName()
                    surname = client.getSurname()
                    rent=[name, surname, number]
                    rents.append(rent)
                    nin = contract.getNINclient()
                    number = 1
            client = self.__comand2.searchClientNin(nin)
            name = client.getName()
            surname = client.getSurname()
            rent = [name, surname, number]
            rents.append(rent)

            list = sorted(rents,key= lambda rent: rent[2])
            for i in list:
                print("Client: ", i[0], " ", i[1], "has ", i[2]," movie rented")

    def show_movie(self):
        """
        :return: top 10 movies after year
        """
        list = self.__comand1.showMovieList()
        sorted_list = sorted(list, key=self.getkey, reverse=True)
        if sorted_list == []:
            print("The movie list is empty")
        else:
            if len(sorted_list) <=10:
                for i in range(0,len(sorted_list)):
                    print(i,".The movie ", str(sorted_list[i].getTitle()), "has appeared in ",sorted_list[i].getYear())
            else:
                for i in range(0,10):
                    for i in range(0, len(sorted_list)):
                        print(i+1, ".The movie ", str(sorted_list[i].getTitle()), "has appeared in ", sorted_list[i].getYear())

    def getkey(self, movie):
        return movie.getYear()

    def first(self):
        """
        :return: returns the first 30% clients with rented movies
        """
        sorted_list = self.__comand3.sort_nin_selection()
        if sorted_list == []:
            print("The list is empty")
        else:
            rents = []
            nin = sorted_list[0].getNINclient()
            number = 0
            for contract in sorted_list:
                if contract.getNINclient() == nin:
                    number += 1
                else:
                    client = self.__comand2.searchClientNin(nin)
                    name = client.getName()
                    surname = client.getSurname()
                    rent = [name, surname, number]
                    rents.append(rent)
                    nin = contract.getNINclient()
                    number = 1
            client = self.__comand2.searchClientNin(nin)
            name = client.getName()
            surname = client.getSurname()
            rent = [name, surname, number]
            rents.append(rent)

            list = sorted(rents, key=lambda rent: rent[0])
            clients_int = int(0.3*len(rents))
            clients_float = float(0.3*len(rents))
            if clients_float-clients_int > 0.5:
                nr = clients_int+1
            else :
                nr = clients_int
            for i in range(0,nr):
                print("Client: ", list[i][0], " ", list[i][1], "has ", list[i][2], " movie rented")

    def show_top5(self):
        """
        :return: the top 5 movie rented
        """
        sorted_list = self.__comand3.sort_id_Movie()
        if sorted_list == []:
            print("the list is empty")
        else:
            rents = []
            id = sorted_list[0].getIDMovie()
            contract = sorted_list[0]
            title = contract.getTitleMovie()
            number = 0
            for contract in sorted_list:
                if contract.getIDMovie() == id:
                    number += 1
                else:
                    title = contract.getTitleMovie()
                    rent = [id, number]
                    rents.append(rent)
                    id = contract.getIDMovie()
                    number = 1
            rent = [id, number]
            rents.append(rent)
            list_sorted_rent = self.__comand3.mergeSort(rents,key=self.getKey3)
            if len(list_sorted_rent) <= 5:
                for i in list_sorted_rent:
                    print("The movie with id : ", i[0], "has been rented", i[1]," times")
            else:
                for i in range(0,5):
                    print("The movie with id : ", list_sorted_rent[i][0], "has been rented", list_sorted_rent[i][1], " times")

    def sort_rents(self,rent):
        list = sorted(rent, key=self.getKey3)
        return list

    def sort_names(self,rent):
        list = sorted(rent, key=self.getKey4)
        return list

    def getKey3(self, x):
        return x[0]

    def getKey4(self, x):
        return x[0]

    def __len__(self,x):
        return len(x)

    def startUi(self):
        """
        Starts the UI
        """
        k = True
        while k is True:
            print("")
            cmd = self.Main()
            if cmd == "x":
                print("Bye!")
                k = False
            if cmd == "Movie":
                ok = True
                self.Movie_Menu()
                while ok is True:
                    print("")
                    o = input("Option:").strip()
                    if o == "random":
                        self.randomMovie()
                        o = "show"
                    if o == "add":
                        self.createMovie()
                        o = "show"
                    if o == "remove":
                        self.removeMovie()
                        o = "show"
                    if o == "search":
                        self.searchMovie()
                    if o == "show":
                        if self.__comand1.showMovieList() is not False:
                            m = self.__comand1.showMovieList()
                            if m != []:
                                print("\nThe movie list:")
                                for i in m:
                                    print(i)
                        else:
                            print("The movie list is empty.")
                    if o == "back":
                        ok = False
                    if o == "undo":
                        self.undoMovies()
                        o = "show"
                    if o == "update":
                        self.updateMovies()
                        o = "update"

            if cmd == "Client":
                ok = True
                self.Client_Menu()
                while ok is True:
                    print("")
                    o = input("Option:").strip()
                    if o == "random":
                        self.randomClient()
                        o = "show"
                    if o == "add":
                        self.createClient()
                        o = "show"
                    if o == "remove":
                        self.removeClient()
                        o = "show"
                    if o == "search":
                        self.searchClient()
                    if o == "show":
                        if self.__comand2.showClientList() is not False:
                            m = self.__comand2.showClientList()
                            if m != []:
                                print("\nThe client list:")
                                for i in m:
                                    print(i)
                        else:
                            print("The client list is empty.")
                    if o == "back":
                        ok = False
                    if o == "undo":
                        self.undoClient()
                        o = "show"
                    if o == "update":
                        self.updateClients()
                        o = "update"

            if cmd == "Contracts":
                ok = True
                self.Contract_Menu()
                while ok is True:
                    print("")
                    o = input("Option:").strip()
                    if o == "add":
                        self.createContractInsert()
                        o = "show"
                    if o == "remove":
                        self.removeContract()
                        o = "show"
                    if o == "search":
                        self.searchContracts()
                    if o == "show":
                        if self.__comand3.showContractList() is not False:
                            m = self.__comand3.showContractList()
                            if len(m) >= 1:
                                print("\nThe contract list:")
                                for i in m:
                                    print(i)
                        else:
                            print("The contract list is empty.")
                    if o == "show rent":
                        self.show_rent()
                    if o == "top":
                        self.show_top5()
                    if o == "show name":
                        self.show_name()
                    if o == "show movie":
                        self.show_movie()
                    if o == "first":
                        self.first()
                    if o == "back":
                        ok = False

