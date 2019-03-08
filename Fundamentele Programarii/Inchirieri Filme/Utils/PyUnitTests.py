import unittest
from Repository.ClientRepository import *
from UI.ContractsService import *
from UI.MovieController import *
from UI.ClientController import*
from FileRepository.ContractFileRepo import ContractFileRepo
from FileRepository.ClientFileRepo import ClientFileRepo
from FileRepository.MovieFileRepo import MovieFileRepo

class TestCase(unittest.TestCase):

    def setUp(self):
        self.film = Movie(1, "We're the Millers", "Comedy", "Awesome stuff. :)", 2003)
        self.movie = Movie(2, "The internship", "Who would have thought so ?", "Comedy", 2015)
        self.client = Client('1', "Alina", "Faluta", "1234567890123")
        self.contract = Contract(1, 2, "Blade", '1234567890123')
        self.contractul = Contract(1, 2, 'Alice', '1234567890123')
    def tearDown(self):
        pass

    def testIDMovie(self):
        self.assertEqual(self.film.getId(), 1)

    def testTitleMovie(self):
        self.assertEqual(self.film.getTitle(), "We're the Millers")

    def testTypeMovie(self):
        self.assertTrue(self.film.getType() == "Comedy")

    def testDescriptionMovie(self):
        self.assertTrue(self.film.getDescription() == "Awesome stuff. :)")

    def testYearMovie(self):
        self.assertEqual(self.film.getYear(), 2003)

    def testMovieValidator_1(self):
        """
            Test validate
        """
        validator = movieValidator()
        movie = Movie("", "Ion", "str", "sakjdkj", 234)
        self.assertRaises(ValueError, validator.validate, movie)

    def testMovieValidator_2(self):
        validator = movieValidator()
        movie = Movie("", "", "", "", "")
        self.assertRaises(ValueError, validator.validate, movie)

    def testMovieValidator_3(self):
        validator = movieValidator()
        movie = Movie(1, "titlu", "descriere", "tip", -23)
        self.assertRaises(ValueError, validator.validate, movie)

    def testMovieValidator_4(self):
        validator = movieValidator()
        movie = Movie(1, "titlu", "descriere", "tip", 2.3)
        self.assertRaises(ValueError, validator.validate, movie)


    def testIdClient(self):
        self.assertTrue(self.client.getId() == '1')

    def testNINClient(self):
        self.assertTrue(self.client.getNIN() == '1234567890123')

    def testNameClient(self):
        self.assertTrue(self.client.getName() == 'Alina')

    def testSurnameClient(self):
        self.assertTrue(self.client.getSurname() == 'Faluta')


    def TestIDContract(self):
        """
            test the contract class
        """
        self.assertEquals(self.contract.getIDMovie(),2)

    def TestNINContract(self):
        self.assertEquals(self.contract.getNINclient(),'1234567890123')

    def TestSetContract(self):
        self.contract.setNIN('2233445566771')
        self.contract.setId(3)
        self.assertTrue(self.contract.getIDMovie() == 3)
        self.assertTrue(self.contract.getNINclient() == '2233445566771')


    def test_storeMovie(self):
        validator = movieValidator()
        repository = InMemoryRepository()
        self.assertTrue(repository.size() == 0)
        repository.storeMovie(self.movie)
        self.assertTrue(repository.size() == 1)


    def test_removeMovie(self):
        repository = InMemoryRepository()
        repository.storeMovie(self.film)
        repository.storeMovie(self.movie)
        repository.removeMovie("We're the Millers")
        self.assertEqual(repository.size(), 1)

    def test_undo_movie_1(self):
        repository = InMemoryRepository()
        repository.storeMovie(self.film)
        self.assertTrue(repository.size() == 1)
        repository.undo()
        self.assertTrue(repository.size() == 0)

    def test_undo_movie_2(self):
        repository = InMemoryRepository()
        repository.storeMovie(self.film)
        repository.storeMovie(self.movie)
        self.assertTrue(repository.size() == 2)
        repository.removeMovie("The internship")
        repository.undo()
        self.assertTrue(repository.size() == 2)

    def testStore(self):
        repo = ContractRepository()
        repo.storeContracts(self.contract)
        self.assertTrue(repo.size() == 1)
        self.assertRaises(ValueError, repo.storeContracts, self.contractul)

    def testRemove(self):
        repo = ContractRepository()
        repo.storeContracts(self.contract)
        self.assertTrue(repo.size() == 1)
        repo.removeContract(1)
        self.assertTrue(repo.size()==0)

    def test_undo_Contract(self):
        repo = ContractRepository()
        repo.storeContracts(self.contract)
        repo.undo()
        self.assertTrue(repo.size() == 0)
        repo.storeContracts(self.contractul)
        repo.removeContract(3)
        self.assertTrue(repo.size() == 1)

    def test_storeClient(self):
        repository = inMemoryRepositoryClients()
        self.assertTrue(repository.size() == 0)
        repository.storeClient(self.client)
        self.assertTrue(repository.size() == 1)

    def test_removeClient(self):
        repository = inMemoryRepositoryClients()
        repository.storeClient(self.client)
        client = Client(2, "Alina", "Faluta", "1224567890123")
        repository.storeClient(client)
        repository.removeClient("1234567890123")
        self.assertTrue(repository.size() == 1)

    def test_undo_Client(self):
        repository = inMemoryRepositoryClients()
        repository.storeClient(self.client)
        repository.undo()
        repository.storeClient(self.client)
        self.assertRaises(ValueError,repository.removeClient, '1234367890123')
        repository.undo()
        self.assertTrue(repository.size() == 0)

    def test_createContract(self):
        repository = ContractRepository()
        control = ContractService(repository)
        control.createContract(2, 'Narnia', '1234567890123')
        control.deleteContract(1)
        control.deleteContract(2)
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
        self.assertTrue(rents[0][0] == '1234767894123')
        self.assertTrue(rents[0][1] == 1)

    def test_sort(self):
        repo = ContractRepository()
        list = repo.get_all()
        serv = ContractService(repo)
        list = serv.selectionSort(list, key = lambda x:x.getNinClient())
        self.assertTrue(list == [])

    def test_createMovie(self):
        repository = InMemoryRepository()
        control = MovieService(repository)
        control.createMovie("Blade 1", "Action", "Vamps getting slayed.", 2016)
        try:
            control.createMovie("Blade 2", "Action", "Vamps getting slayed again.", 2018)
            assert False
        except:
            assert True

    def test_storeMovieFile(self):
        fileName = "testMovie.txt"
        repository = MovieFileRepo(fileName)
        repository.removeAll()
        movie = Movie('2', "We're the Millers", "Awesome stuff. :)", "Comedy", 2014)
        repository.storeMovie(movie)
        self.assertTrue(repository.size() == 1)
        movie2 = Movie('2', "The internship", "Who would have thought so ?", "Comedy", 2015)
        self.assertRaises(ValueError,repository.storeMovie, movie2)


    def test_removeMovieFile(self):
        fileName = "testMovie.txt"
        repository = MovieFileRepo(fileName)
        repository.removeAll()
        repository.storeMovie(self.movie)
        repository.storeMovie(self.film)
        repository.removeMovie("We're the Millers")
        self.assertTrue(repository.size() == 1)

    def test_createContractFile(self):
        fileName = "testContract.txt"
        repository = ContractFileRepo(fileName)
        repository.removeAll()
        repository.storeContracts(self.contract)
        self.assertTrue(repository.size() == 1)

    def test_storeClientFile(self):
        fileName = "testClient.txt"
        repository = ClientFileRepo(fileName)
        repository.removeAll()
        self.assertTrue(repository.size() == 0)
        repository.storeClient(self.client)
        self.assertTrue(repository.size() == 1)


if __name__ == 'main':
    unittest.main()