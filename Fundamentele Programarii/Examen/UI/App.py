import Repository
from Repository.FileRepository import JucatoriRepo
from UI.Controller import Control
from UI.ui import UIJucator

repo = JucatoriRepo('jucatori.txt')
controller = Control(repo)
ui = UIJucator(controller)

ui.main()