from UI.Controller import Controller
from UI.console import UI

from Repository.FlightRepository import FileRepository

repo = FileRepository("flights.txt")
controller = Controller(repo)
ui = UI(controller)

ui.main()