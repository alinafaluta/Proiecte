from Domain.Contract import Contract,ContractValidator
from Repository.ContractRepository import ContractRepository


class ContractService:

    def __init__(self, repo):
        self.repository = repo
        self.__index = 0

    def createContract(self, idMovie, title, nin):
        """
            add a contract in the list
            returns the contract
        """
        if self.repository.get_all() != []:
            list = self.repository.get_all()
            id_list = int(list[-1].getIdContract())
            self.__index = id_list+1
        else:
            self.__index = self.__index + 1
        id = self.__index
        contracte = Contract(id, idMovie, title, nin)
        validator = ContractValidator()
        try:
            if validator.validate(contracte):
                self.repository.storeContracts(contracte)
                return True
            else:
                if validator.validate(contracte) not in None:
                    self.__index = self.__index - 1
                    return validator.validate(contracte)
        except ValueError as ex:
            self.__index = self.__index - 1
            print(ex)

    def deleteContract(self, id):
        """
            deletes a movie by id
        """
        return self.repository.removeContract(id)


    def sort_nin(self):
        customlist = self.repository.get_all()
        list = sorted(customlist, key=self.getKey)
        return list

    # TODO: Selection sort
    def selectionSort(self,list,*,key = None, reverse = None):
        for i in range(0,len(list) - 1):
            positionOfMax = i
            for j in range(i+1, len(list)):
                if key:
                    if reverse:
                        if key(list[j]) > key(list[positionOfMax]):
                            positionOfMax = j
                    else:
                        if key(list[j]) < key(list[positionOfMax]):
                            positionOfMax = j
                else:
                    if reverse:
                        if list[j] > list[positionOfMax]:
                            positionOfMax = j
                    else:
                        if list[j]<list[positionOfMax]:
                            positionOfMax = j
            if i < positionOfMax:
                list[i],list[positionOfMax]=list[positionOfMax],list[i]
        return list

    # todo Merge Sort

    def mergeSort(self,list,*,key = None, reverse = None):
        if key:
            if reverse:
                if len(list) >1:
                    mid = len(list) // 2
                    lefthalf = list[:mid]
                    righthalf =list[mid:]

                    self.mergeSort(lefthalf,key=key,reverse=reverse)
                    self.mergeSort(righthalf,key=key,reverse=reverse)

                    i = 0
                    j = 0
                    k = 0
                    while i < len(lefthalf) and j < len(righthalf):
                        if key(lefthalf[i]) > key(righthalf[j]):
                            list[k] = lefthalf[i]
                            i = i + 1
                        else:
                            list[k] = righthalf[j]
                            j = j + 1
                        k = k + 1

                    while i < len(lefthalf):
                        list[k] = lefthalf[i]
                        i = i + 1
                        k = k + 1

                    while j < len(righthalf):
                        list[k] = righthalf[j]
                        j = j + 1
                        k = k +1
            else:
                if len(list) >1:
                    mid = len(list) // 2
                    lefthalf = list[:mid]
                    righthalf =list[mid:]

                    self.mergeSort(lefthalf, key=key, reverse=reverse)
                    self.mergeSort(righthalf, key=key, reverse=reverse)

                    i = 0
                    j = 0
                    k = 0
                    while i < len(lefthalf) and j < len(righthalf):
                        if key(lefthalf[i]) < key(righthalf[j]):
                            list[k] = lefthalf[i]
                            i = i + 1
                        else:
                            list[k] = righthalf[j]
                            j = j + 1
                        k = k + 1

                    while i < len(lefthalf):
                        list[k] = lefthalf[i]
                        i = i + 1
                        k = k + 1

                    while j < len(righthalf):
                        list[k] = righthalf[j]
                        j = j + 1
                        k = k + 1
        return list


    def sort_nin_selection(self):
        return self.selectionSort(self.repository.get_all(),key =lambda contract: contract.getNINclient())

    def sort_id_Movie(self):
        customlist = self.repository.get_all()
        list = self.mergeSort(customlist, key=self.getKey2)
        return list
    # TODO: shake sort
    def shake_sort(self, list,*,key = None,reverse = None):
        for k in range(len(list) - 1, 0, -1):
            swapped = False
            for i in range(k, 0, -1):
                if key:
                    if reverse:
                        if key(list[i]) > key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                    else:
                        if key(list[i]) < key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                else:
                    if reverse:
                        if key(list[i]) > key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                    else:
                        if key(list[i]) < key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True

            for i in range(k):
                if key:
                    if reverse:
                        if key(list[i]) > key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                    else:
                        if key(list[i]) < key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                else:
                    if reverse:
                        if key(list[i]) > key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
                    else:
                        if key(list[i]) < key(list[i - 1]):
                            list[i], list[i - 1] = list[i - 1], list[i]
                            swapped = True
            if not swapped:
                return list

    def getKey(self, contract):
        return contract.getNINclient()


    def getKey2(self, contract):
        return str(contract.getIDMovie())

    def showContractList(self):
        """
            Returns the full contract list.
        """
        list = self.repository.get_all()
        if list != []:
            return list
        else:
            return False

    def updateContract(self, id, newIdMovie, newNin):
        allContracts = self.repository.get_all()
        m = []
        for contract in allContracts:
            if contract.getIdContract() == id:
                contract.setId(newIdMovie)
                contract.setNIN(newNin)
                m.append(contract)
        if len(m) > 0:
            return m

    def searchContract(self,id):
        m = []
        allContracts = self.repository.get_all()
        for contract in allContracts:
            if contract.getIdContract() == id:
                m.append(contract)
        if len(m) != 0:
            return m
        else:
            return None

    def undoContractList(self):
        """
            undoes the contract list once
        """
        self.repository.undo()

    def getALL(self):
        return self.repository.get_all()