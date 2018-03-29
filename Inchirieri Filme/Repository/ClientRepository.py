from Domain.Clients import Client
from copy import deepcopy

class inMemoryRepositoryClients:
    def __init__(self):
        self.clients = []
        self.__undo = []

    def storeClient(self, customer):
        """
        Stores a client
        """
        if self.clients != []:
            self.__undo.append(deepcopy(self.clients))
        for i in self.clients:
            if i.getNIN() == customer.getNIN():
                raise ValueError
        self.clients.append(customer)
        return customer

    def removeClient(self, CNP):
        """
        Deletes a client by CNP.
        """

        Remove = []
        for i in self.clients:
            if i.getNIN() != CNP:
                Remove.append(i)
        if len(Remove) == self.size():
            raise ValueError("No client with this NIN")
        else:
            self.__undo.append(deepcopy(self.clients))
            self.setClients(Remove)

    def size(self):
        """
         Returns how many different movies are in the repository.
        """
        return self.size_Rec(self.clients)
    # TODO: Recursiv Size
    def size_Rec(self,list):
        if len(list)==0:
            return 0
        return 1+self.size_Rec(list[1:])

    def getAll(self):
        return self.clients

    def undo(self):
        if len(self.__undo) > 1:
            self.clients,self.__undo[-1]= self.__undo[-1], self.clients
            del self.__undo[-1]
        elif len(self.__undo) == 1:
            self.clients, self.__undo[0] = self.__undo[0], self.clients
            del self.__undo[0]
        elif len(self.__undo) == 0:
            lista_vida = []
            self.clients, lista_vida = lista_vida, self.clients

    def setClients(self,list):
        self.clients = list