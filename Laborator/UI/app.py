from UI.LabController import Controller
from UI.LabUi import LabUi
from Repository.LabRepository import FileLabRepo
from Repository.StudentRepository import FileStudRepo

studRepo = FileStudRepo('student.txt')
labRepo = FileLabRepo('labs.txt')

controler = Controller(studRepo,labRepo)

ui = LabUi(controler)

ui.run()