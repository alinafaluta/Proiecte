using Laborator12_13.domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Laborator12_13.Domain
{
    class EntityToFileMapping
    {
        public static Student CreateStudent(string line)
        {
            string[] fields = line.Split(','); // new char[] { ',' } 
            Student student = new Student(int.Parse(fields[0]), fields[1], int.Parse(fields[2]), fields[3], fields[4]);
            return student;
        }



        public static Tema CreateTema(string line)
        {
            string[] fields = line.Split(','); // new char[] { ',' } 
            Tema tema = new Tema(int.Parse(fields[0]), fields[1], int.Parse(fields[2]), int.Parse(fields[3]));
            return tema;
        }

        public static Inregistrare CreateInregistrare(string line)
        {
            string[] fields = line.Split(',');
            Inregistrare inregistrare = new Inregistrare(int.Parse(fields[0]), int.Parse(fields[1]), int.Parse(fields[2]), float.Parse(fields[3]));
            return inregistrare;
        }
    }
}
