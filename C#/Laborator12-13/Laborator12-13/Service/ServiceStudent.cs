using System;
using System.Collections.Generic;
using System.Text;
using Laborator12_13.repository;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using System.Linq;

namespace Laborator12_13.Service
{
    class ServiceStudent
    {
        ICrudRepository<int,Student> studentRepo;

        public ServiceStudent(InMemoryRepository<int, Student> studentRepo)
        {
            this.studentRepo = studentRepo;
        }

        public ServiceStudent(ICrudRepository<int, Student> studentRepo)
        {
            this.studentRepo = studentRepo;
        }

        public void addStudent(int idStudent, String nume, int grupa, String email, String profesor)
        {
            if (studentRepo.findOne(idStudent) != null)
                throw new ValidationException("Studentul cu acest numar matricol exista deja");
            Student s = new Student(idStudent, nume, grupa, email, profesor);
            studentRepo.save(s);
        }

        public void deleteStudent(int id)
        {
            if (studentRepo.findOne(id) == null)
                throw new ValidationException("Nu exista nici un student cu acest numar matricol \n");
            studentRepo.delete(id);
        }

        public void updateStudent(int idStudent, String nume, int grupa, String email, String profesor)
        {
            Student s = new Student(idStudent, nume, grupa, email, profesor);
            if (studentRepo.update(s) != null)
            {
                throw new ValidationException("Studentul pe care doriti sa il modificati nu exista! \n");
            }
        }

        public Student findStudent(int id)
        {
            return studentRepo.findOne(id);
        }
        public IEnumerable<Student> allStudents()
        {
            return studentRepo.findAll();
        }

        private List<Student> FilterBy(Predicate<Student> predicate)
        {
            List<Student> angajati = studentRepo.findAll().ToList();
            return angajati.Where((x, y) => { return predicate(x); }).ToList();
        }

        public List<Student> FilterByGrua(int level)
        {
            return FilterBy(x => x.Grupa.Equals(level));
        }
    }
}
