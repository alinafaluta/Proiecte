using System;
using System.Collections.Generic;
using System.Text;
using Laborator12_13.repository;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using System.Globalization;

namespace Laborator12_13.Service
{
    public class Service
    {
        InMemoryRepository<int, Inregistrare> catalogRepo;
        InMemoryRepository<int, Tema> temaRepo;
        InMemoryRepository<int, Student> studentRepo;

        public Service(InMemoryRepository<int, Inregistrare> catalogRepo, InMemoryRepository<int, Tema> temaRepo, InMemoryRepository<int, Student> studentRepo)
        {
            this.catalogRepo = catalogRepo;
            this.temaRepo = temaRepo;
            this.studentRepo = studentRepo;
        }

        public bool AdaugaStudent(Student student)
        {
            if (studentRepo.save(student) == default(Student))
                return true;
            else return false;
        }

        public bool StergeStudent(int id)
        {
            if (studentRepo.delete(id) == default(Student))
                return false;
            else return true;
        }

        public bool ActualizeazaStudent(Student student)
        {
            if (studentRepo.update(student) == default(Student))
                return true;
            else return false;
        }

        public Student CautaStudent(int id)
        {
            return studentRepo.findOne(id);
        }

        public IEnumerable<Student> ListaStudenti()
        {
            return studentRepo.findAll();
        }

        public bool AdaugaTema(Tema tema)
        {
            if (temaRepo.save(tema) == default(Tema))
                return true;
            return false;
        }

        public Tema CautaTema(int id)
        {
            return temaRepo.findOne(id);
        }

        public IEnumerable<Tema> ListaTeme()
        {
            return temaRepo.findAll();
        }

        public static int GetWeekNumber(DateTime now)
        {
            CultureInfo ci = CultureInfo.CurrentCulture;
            int weekNumber = ci.Calendar.GetWeekOfYear(now, CalendarWeekRule.FirstFourDayWeek, DayOfWeek.Monday);
            return weekNumber;
        }

        public int GetLabNumber()
        {
            int currentWeek = GetWeekNumber(DateTime.Now);
            int dif = currentWeek - 39;
            if (dif < 1 || dif > 16) return default(int);
            if (currentWeek.Equals(13) || currentWeek.Equals(14))
                return 12;
            if (currentWeek.Equals(15)) return dif - 1;
            if (currentWeek.Equals(16)) return dif - 2;
            return dif;
        }

        public bool PrelungireDeadline(int id, int data)
        {
            Tema t = temaRepo.findOne(id);

            //Daca nu modific nimic
            if (t.Deadline == data)
                return false;

            //Nu pot sa modific deadlineul daca el deja a fost depasit
            if (t.Deadline < GetLabNumber())
                return false;

            if (t != default(Tema))
            {
                //In caz ca dau un deadline mai mic fata de data de predare
                try
                {
                    t.Deadline = data;
                    temaRepo.update(t);
                }
                catch (ValidationException)
                {
                    return false;
                }
            }

            return true;
        }

        public float CalculeazaInregistrare(String data, String InregistrareProf, Tema tema)
        {
            float Inregistrare = float.Parse(InregistrareProf);
            int dif = Int32.Parse(data) - tema.Deadline;
            if (dif > 0 && dif <= 2)
            {
                return Inregistrare - dif * 2.5f;
            }
            else if (dif <= 0)
            {
                return (float)Inregistrare;
            }
            else
            {
                return 1f;
            }
        }

        /* public bool AdaugaInregistrare(Inregistrare entity, bool motivat)
         {
             if (studentRepo.findOne(entity.IdStudent) == default(Student))
                 return false;
             if (temaRepo.findOne(entity.IdTema) == default(Tema))
                 return false;
             IEnumerable<Inregistrare> it = catalogRepo.findAll();
             bool ok = false;
             foreach (Inregistrare i in it)
                 if (i == default(Inregistrare))
                     ok = true;
             if (ok ==false)
                 return false;
            // if (!motivat)
             //{
                /* float Inregistrare = CalculeazaInregistrare(DateTime.Now, studentRepo.findOne(entity.IdStudent).Profesor, temaRepo.findOne(entity.TemaID));
                 entity.InregistrareProf = Inregistrare.ToString();*/
        //}
        //if (catalogRepo.Save(entity) == default(Inregistrare))
        //  return true;
        //else return false;}*/
    }
}
