from Repository.ClientRepository import inMemoryRepositoryClients
from Repository.MovieRepository import InMemoryRepository
from Repository.ContractRepository import ContractRepository
from FileRepository.ContractFileRepo import ContractFileRepo
from FileRepository.MovieFileRepo import MovieFileRepo
from FileRepository.ClientFileRepo import ClientFileRepo
from UI.ClientController import ClientService
from UI.MovieController import MovieService
from UI.ContractsService import ContractService
from UI.console import *

repository = InMemoryRepository()
controller = MovieService(repository)

repository2 = inMemoryRepositoryClients()
controller2 = ClientService(repository2)

repository3 = ContractRepository()
controller3 = ContractService(repository3)
ui = Console(controller, controller2, controller3)


movies = MovieFileRepo("Movies.txt")
clients = ClientFileRepo("Clients.txt")
contracts = ContractFileRepo("Contracts.txt")
MovieController = MovieService(movies)
ClientController = ClientService(clients)
ContractController = ContractService(contracts)

ui_file = Console(MovieController, ClientController, ContractController)
ui_file.startUi()

"""
TODO:
6.Se dă o listă de numere întregi a1,...an generați toate sub-secvențele cu proprietatea că
suma numerelor este divizibul cu N dat.
"""