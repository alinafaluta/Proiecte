from copy import deepcopy


class ContractRepository:

    def __init__(self):
        self.__contracts = []
        self.__undo = []

    def storeContracts(self, contract):

        if self.__contracts != []:
            self.__undo.append(deepcopy(self.__contracts))
        for i in self.__contracts:
            if i.getNINclient() == contract.getNINclient() and i.getIDMovie() == contract.getIDMovie():
                raise ValueError("Movie already rented by this client")
        self.__contracts.append(contract)

    def removeContract(self,id):
        updated_contracts = []
        for i in self.__contracts:
            if i.getIdContract() != id:
                updated_contracts.append(i)
        self.__contracts = updated_contracts
        return self.__contracts

    def setContracts(self, x):
        self.__contracts = x

    def size(self):
        return len(self.__contracts)

    def get_all(self):
        return self.__contracts

    def __len__(self):
        return len(self.__contracts)

    def undo(self):
        if len(self.__undo) > 1:
            self.__contracts,self.__undo[-1]= self.__undo[-1], self.__contracts
            del self.__undo[-1]
        elif len(self.__undo) == 1:
            self.__contracts, self.__undo[0] = self.__undo[0], self.__contracts
            del self.__undo[0]
        elif len(self.__undo) == 0:
            lista_vida = []
            self.__contracts, lista_vida = lista_vida, self.__contracts
