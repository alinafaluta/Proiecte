using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator12_13.domain
{
    public class Student : IHasID<int>
    {
        private int idStudent;
        private String nume;
        private int grupa;
        private String email;
        private String profesor;

        public Student(int idStudent, string nume, int grupa, string email, string profesor)
        {
            this.idStudent = idStudent;
            this.nume = nume;
            this.grupa = grupa;
            this.email = email;
            this.profesor = profesor;
        }

        public int ID { get => IdStudent; set => IdStudent=value; }
        public int IdStudent { get => idStudent; set => idStudent = value; }
        public string Nume { get => nume; set => nume = value; }
        public int Grupa { get => grupa; set => grupa = value; }
        public string Email { get => email; set => email = value; }
        public string Profesor { get => profesor; set => profesor = value; }

        public override bool Equals(object obj)
        {
            var student = obj as Student;
            return student != null &&
                   idStudent == student.idStudent &&
                   nume == student.nume &&
                   grupa == student.grupa &&
                   email == student.email &&
                   profesor == student.profesor;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(idStudent, nume, grupa, email, profesor);
        }

        public override string ToString()
        {
            return ID + "," + Nume + "," + Grupa + "," + Email + "," + Profesor;
        }
    }
}
