from Repository.ClientRepository import *
from UI.ContractsService import *
from UI.MovieController import *
from UI.ClientController import*
from FileRepository.ContractFileRepo import ContractFileRepo
from FileRepository.ClientFileRepo import ClientFileRepo
from FileRepository.MovieFileRepo import MovieFileRepo


def testMovie():
    film = Movie(1, "We're the Millers", "Comedy", "Awesome stuff. :)", 2003)
    assert film.getId() == 1
    assert film.getTitle() == "We're the Millers"
    assert film.getType() == "Comedy"
    assert film.getDescription() == "Awesome stuff. :)"
    assert film.getYear() == 2003
    film.setDescription('alabalaaa')
    film.setTitle('Titlu2')
    film.setYear(2004)
    assert film.getTitle() == "Titlu2"
    assert film.getDescription() == "alabalaaa"
    assert film.getType() == "Comedy"
    assert film.getYear() == 2004


testMovie()


def testMovieValidator():
    """
        Test validate
    """
    validator = movieValidator()
    movie = Movie("", "Ion", "str", "sakjdkj", 234)
    try:
        validator.validate(movie)
        assert False
    except ValueError:
        assert True

    movie = Movie("", "", "", "", "")
    try:
        validator.validate(movie)
        assert False
    except ValueError:
        assert True

    movie = Movie(1, "titlu", "descriere", "tip", -23)
    try:
        validator.validate(movie)
        assert False
    except ValueError:
        assert True

    movie = Movie(1, "titlu", "descriere", "tip", 2.3)
    try:
        validator.validate(movie)
    except ValueError:
        assert True


testMovieValidator()


def testClient():
    st = Client(1, 'Ion', 'Popescu', '1090838836372')
    assert st.getId() == 1
    assert st.getNIN() == '1090838836372'
    assert st.getName() == 'Ion'
    assert st.getSurname() == 'Popescu'
    st.setName('Maria')
    st.setSurname('Ionescu')
    st.setNIN(st.getNIN())
    assert st.getId() == 1
    assert st.getSurname() == 'Ionescu'
    assert st.getName() == 'Maria'


testClient()


def TestContract():
    """
        test the contract class
    """
    contract = Contract(1, 2, "Blade", '1234567890123')
    assert contract.getIDMovie() == 2
    assert contract.getNINclient() == '1234567890123'

    contract.setNIN('2233445566771')
    contract.setId(3)
    assert contract.getIDMovie() == 3
    assert contract.getNINclient() == '2233445566771'


def testContractValidator():
    """
            Test validate
    """
    validator = ContractValidator()
    contract = Contract(1, 3, "Narnia", '2233445566771')
    try:
        validator.validate(contract)
        assert True
    except ValueError:
        assert False

    contract = Contract("", "", "", "")
    try:
        validator.validate(contract)
        assert False
    except ValueError:
        assert True

        contract = Contract(3, 2, 'Avatar', "1234567891234")
    try:
        validator.validate(contract)
    except ValueError:
        assert True


TestContract()
testContractValidator()


def test_storeMovie():
    movie = Movie(2, "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
    repository = InMemoryRepository()
    assert repository.size() == 0
    repository.storeMovie(movie)
    assert repository.size() == 1
    movie2 = Movie(2, "The internship", "Who would have thought so ?", "Comedy", 2015)
    try:
        repository.storeMovie(movie2)
        assert False
    except ValueError:
        assert True


def test_removeMovie():
    repository = InMemoryRepository()
    movie = Movie(1, "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
    repository.storeMovie(movie)
    movie = Movie(2, "The internship", "Who would have thought so ?", "Comedy", 2015)
    repository.storeMovie(movie)
    repository.removeMovie("We're the Millers")
    assert repository.size() == 1


def test_undo():
    movie = Movie(2, "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
    repository = InMemoryRepository()
    repository.storeMovie(movie)
    assert repository.size() == 1
    repository.undo()
    assert repository.size() == 0
    repository.storeMovie(movie)
    movie2 = Movie(1, "The internship", "Who would have thought so ?", "Comedy", 2015)
    repository.storeMovie(movie2)
    assert repository.size() == 2
    repository.removeMovie("The internship")
    repository.undo()
    assert repository.size() == 2


test_storeMovie()
test_undo()
test_removeMovie()


def testStore():
    contract = Contract(1, 2, 'Alice', '1234567890123')
    repo = ContractRepository()
    assert repo.size() == 0
    repo.storeContracts(contract)
    assert repo.size() == 1

    contract = Contract(4, 2, 'Anabelle', '1234567890123')
    assert repo.size() == 1
    try:
        repo.storeContracts(contract)
        assert False
    except ValueError:
        assert True


def testRemove():
    contract = Contract(1, 2, 'Dumbo', '1234567890123')
    repo = ContractRepository()
    repo.storeContracts(contract)
    assert repo.size() == 1
    repo.removeContract(1)
    assert repo.size() == 0


def test_undo():
    contract = Contract(1, 2, 'Narnia', '1234567890123')
    repo = ContractRepository()
    repo.storeContracts(contract)
    repo.undo()
    assert repo.size() == 0
    contract = Contract(3, 4, "The Smurfs", '1234767890123')
    repo.storeContracts(contract)
    repo.removeContract(3)
    assert repo.size() == 0


test_undo()
testRemove()
testStore()


def test_storeClient():
    client = Client(1, "Alina", "Faluta", "1234567890123")
    repository = inMemoryRepositoryClients()
    assert repository.size() == 0
    repository.storeClient(client)
    assert repository.size() == 1


def test_removeClient():
    repository = inMemoryRepositoryClients()
    client = Client(1, "Alina", "Faluta", "1234567890123")
    repository.storeClient(client)
    client = Client(2, "Alina", "Faluta", "1224567890123")
    repository.storeClient(client)
    repository.removeClient("1234567890123")
    assert repository.size() == 1


def test_undo():
    client = Client(1, "Alina", "Faluta", "1234567890123")
    repository = inMemoryRepositoryClients()
    repository.storeClient(client)
    repository.undo()
    assert repository.size() == 0
    client = Client(1, "Alina", "Faluta", "1234367890123")
    repository.storeClient(client)
    repository.removeClient('1234367890123')
    repository.undo()
    assert repository.size() == 1


test_storeClient()
test_undo()
test_removeClient()


def test_createContract():
    control = ContractService()
    control.createContract(2, 'Narnia', '1234567890123')
    assert len(control.getALL()) == 1
    try:
        control.createContract(4, 'Avatar', '1234767894123')
        assert False
    except:
        assert True
    assert len(control.getALL()) == 2
    #print(control.getALL())
    control.deleteContract(1)
    #print(control.getALL())
    control.deleteContract(2)
    #print(control.getALL())
    assert len(control.getALL()) == 0
    control.createContract(3, 'Anabelle', '2234567890123')
    control.createContract(6, 'SpiderMan', '2234567890123')
    control.createContract(2, 'Narnia', '2234567890123')
    control.createContract(4, 'Avatar', '1234767894123')
    list = control.sort_nin()
    sorted_list = list
    rents = []
    nin = sorted_list[0].getNINclient()
    number = 0
    for contract in sorted_list:
        if contract.getNINclient() == nin:
            number += 1
        else:
            rent = [nin, number]
            rents.append(rent)
            nin = contract.getNINclient()
            number = 1
    rent = [nin, number]
    rents.append(rent)
    assert rents[0][0] == '1234767894123'
    assert rents[0][1] == 1
    assert rents[1][0] == '2234567890123'
    assert rents[1][1] == 3


test_createContract()


def test_createMovie():
    repository = InMemoryRepository()
    control = MovieService(repository)
    control.createMovie("Blade 1", "Action", "Vamps getting slayed.", 2016)
    try:
        control.createMovie("Blade 2", "Action", "Vamps getting slayed again.", 2018)
        assert False
    except:
        assert True
    assert len(repository.getAll()) == 2


test_createMovie()


def test_storeMovieFile():
    fileName = "testMovie.txt"
    repository = MovieFileRepo(fileName)
    repository.removeAll()
    movie = Movie('2', "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
    assert repository.size() == 0
    repository.storeMovie(movie)
    assert repository.size() == 1
    movie2 = Movie('2', "The internship", "Who would have thought so ?", "Comedy", 2015)
    try:
        repository.storeMovie(movie2)
        assert False
    except ValueError:
        assert True


test_storeMovieFile()


def test_removeMovieFile():
    fileName = "testMovie.txt"
    repository = MovieFileRepo(fileName)
    repository.removeAll()
    movie = Movie(1, "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
    repository.storeMovie(movie)
    movie = Movie(2, "The internship", "Who would have thought so ?", "Comedy", 2015)
    repository.storeMovie(movie)
    repository.removeMovie("We're the Millers")
    assert repository.size() == 1


test_removeMovieFile()


def test_createContractFile():
    fileName = "testContract.txt"
    repository = ContractFileRepo(fileName)
    repository.removeAll()

    contract = Contract('1','2','Blade','1234567890123')
    repository.storeContracts(contract)
    assert repository.size() == 1


test_createContractFile()


def test_storeClientFile():
    fileName = "testClient.txt"
    repository = ClientFileRepo(fileName)
    repository.removeAll()

    client = Client('1', "Alina", "Faluta", "1234567890123")
    assert repository.size() == 0
    repository.storeClient(client)
    assert repository.size() == 1


test_storeClientFile()