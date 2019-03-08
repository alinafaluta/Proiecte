from Domain.Lab import Laboratory
from Domain.Student import Student

class Controller():

    def __init__(self,studRepo,labRepo):
        self.stud = studRepo
        self.lab = labRepo

    def addLab(self,studentId,labNumber,problemNumber):
        lab = Laboratory(studentId,labNumber,problemNumber)
        if self.stud.findByID(studentId)==False:
            raise ValueError("Student doesn't exist")
        else:
            return self.lab.add(lab)

    def getStudentById(self,id):
        return self.stud.findByID(id)

    def getLabs(self,id):
        return self.lab.findByID(id)

    def all_students(self):
        return self.stud.getAll()

    def getAllbyLab(self,lab):
        all = self.lab.getAll()
        list =[]
        for i in all:
            if i.getLab()== lab:
                list.append(i)
        return list