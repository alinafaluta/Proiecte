using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Laborator12_13.Service;
using Laborator12_13.domain;
using Laborator12_13.Validator;

namespace Laborator12_13.Controller
{
    class Controler
    {
        public ServiceCatalog servC;
        public ServiceStudent servS;
        public ServiceTema servT;

        public Controler(ServiceCatalog servC, ServiceStudent servS, ServiceTema servT)
        {
            this.servC = servC;
            this.servS = servS;
            this.servT = servT;
        }

        public void meniu()
        {
            Console.WriteLine("~~~~~~~~~~~~~~~~~~~Meniu~~~~~~~~~~~~~~~~~~~");
            Console.WriteLine("|   1. Vizualizeaza studentii existenti   |");
            Console.WriteLine("|   2. Vizualizare teme existente         |");
            Console.WriteLine("|   3. Vizualizare catalog                |");
            Console.WriteLine("|   4. Adaugare student                   |");
            Console.WriteLine("|   5. Adaugare tema                      |");
            Console.WriteLine("|   6. Adaugare nota                      |");
            Console.WriteLine("|   7. Stergere student                   |");
            Console.WriteLine("|   8. Update student                     |");
            Console.WriteLine("|   9. Update tema                        |");
            Console.WriteLine("|   10. Extend deadline                   |");
            Console.WriteLine("|   11. Filtreaza studentii dupa grupa    |");
            Console.WriteLine("|   12. Filtreaza temele dupa deadline <  |");
            Console.WriteLine("|   13. Notele unui student dat           |");
            Console.WriteLine("|   14. Rapoarte                          |");
            Console.WriteLine("|   15. Studenti care nu intra in examen  |");
            Console.WriteLine("|    0. Exit                              |");
            Console.WriteLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        /**
         * Afiseaza meniul si selecteaza functiile ce vor fi aplicate in functie de ce comanda va fi data de la tastatura
         */
        public void run()
        {
            meniu();
            while (true)
            {
                Console.WriteLine("Comanda: ");
                String cmd;
                try
                {
                    cmd = Console.ReadLine();
                }
                catch (IOException e)
                {
                    Console.WriteLine("Comanda invalida!");
                    continue;
                }
                if (cmd.Equals("0"))
                {
                    Console.WriteLine("Bye!");
                    break;
                }
                if (cmd.Equals("1"))
                {
                    AfisareStudenti();
                    continue;
                }
                if (cmd.Equals("2"))
                {
                    AfisareTeme();
                    continue;
                }
                if (cmd.Equals("3"))
                {
                    AfisareCatalog();
                    continue;
                }
                if (cmd.Equals("4"))
                {
                    AdaugaStudent();
                    continue;
                }
                if (cmd.Equals("5"))
                {
                    AdaugaTema();
                    continue;
                }
                if (cmd.Equals("6"))
                {
                    AdaugaNotaPenalizare();
                    continue;
                }
                if (cmd.Equals("7"))
                {
                    StergeStudent();
                    continue;
                }
              
                if (cmd.Equals("8"))
                {
                    UpdateStudent();
                    continue;
                }
                if (cmd.Equals("9"))
                {
                    UpdateTema();
                    continue;
              
                }
                if (cmd.Equals("10"))
                {
                    Prelungire();
                    continue;
                }
                if (cmd.Equals("11"))
                {
                    FiltrareGrupa();
                    continue;
                }
                if (cmd.Equals("12"))
                {
                    FiltrareDeadline();
                    continue;
                }

                if (cmd.Equals("12"))
                {
                    FiltrareDeadline();
                    continue;
                }
                if (cmd.Equals("13"))
                {
                    FiltrareNote();
                    continue;
                }
                if (cmd.Equals("14"))
                {
                    Rapoarte();
                    continue;
                }
                if (cmd.Equals("15"))
                {
                    IntraInExamen();
                    continue;
                }
                

            }

        }

        private void IntraInExamen()
        {
            int nr=0;
            IDictionary<int, float> dict = servC.MedieStudent();
            foreach (KeyValuePair<int, float> k in dict)
            {
                if (k.Value <= 4.5)
                {
                    nr++;
                    Console.WriteLine("Studentul cu id-ul: " + k.Key + " are media " + k.Value);
                }
            }
            if(nr==0)
            {
                Console.WriteLine("Toti studentii intra in examen. Tastati 14 pentru a le vedea mediile.");
            }
        }

        private void Rapoarte()
        {
            Console.WriteLine("Mediile studentilor:");
            IDictionary<int, float> dict = servC.MedieStudent();
            foreach(KeyValuePair<int,float> k in dict)
            {
                Console.WriteLine("Studentul cu id-ul: " + k.Key + " are media " + k.Value);
            }

            Console.WriteLine("Mediile temelor:");
            IDictionary<int, float> dictt = servC.MedieTeme();
            foreach (KeyValuePair<int, float> k in dictt)
            {
                Console.WriteLine("Tema cu numarul " + k.Key + " are media " + k.Value);
            }
        }

        private void FiltrareNote()
        {
            Console.WriteLine("Dati id-ul studentului:");
            string cmd;
            try
            {
                cmd = Console.ReadLine();
                IEnumerable<Inregistrare> it = servC.FilterByStudent(int.Parse(cmd));
                foreach (Inregistrare s in it)
                    Console.WriteLine(s.ToString());
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        private void FiltrareDeadline()
        {
            Console.WriteLine("Dati deadline:");
            string cmd;
            try
            {
                cmd = Console.ReadLine();
                IEnumerable<Tema> it = servT.FilterByDeadline(int.Parse(cmd));
                foreach (Tema s in it)
                    Console.WriteLine(s.ToString());
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }

        }

        private void FiltrareGrupa()
        {
            Console.WriteLine("Dati grupa:");
            string cmd;
            try
            {
                cmd = Console.ReadLine();
                IEnumerable<Student> it = servS.FilterByGrua(int.Parse(cmd));
                foreach (Student s in it)
                    Console.WriteLine(s.ToString());
            }
            catch(ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
    
        }

        /**
         * Afiseaza toti studentii
         */
        void AfisareStudenti()
        {
            IEnumerable<Student> it = servS.allStudents();
            foreach (Student s in it)
                Console.WriteLine(s.ToString());

        }

        /**
         * Afiseaza toate temele
         */
        void AfisareTeme()
        {
            IEnumerable<Tema> it = servT.allTeme();
            foreach (Tema s in it)
                Console.WriteLine(s.ToString());

        }

        /**
         * Afiseaza toate notele din catalog
         */
        void AfisareCatalog()
        {
            IEnumerable<Inregistrare> it = servC.allNote();
            foreach (Inregistrare s in it)
                Console.WriteLine(s.ToString());


        }

        /**
         * Citeste de la tastatura numarul matricol, numele , grupa , email-ul si profesorul unui student si il adauga daca acesta este valid
         * In caz contrar afiseaza erorile sau mesajele corespunzatoare
         */
        void AdaugaStudent()
        {
            Console.WriteLine("Numarul matricol (ID): ");
            try
            {
                String id = Console.ReadLine();
                Console.WriteLine("Numele ");
                String numele = Console.ReadLine();
                Console.WriteLine("Grupa: ");
                String grupa = Console.ReadLine();
                Console.WriteLine("Email: ");
                String email = Console.ReadLine();
                Console.WriteLine("Profesor: ");
                String prof = Console.ReadLine();

                servS.addStudent(Int32.Parse(id), numele, Int32.Parse(grupa), email, prof);
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }

        }

        /**
         * Citeste de la tastatura numarul, descriera , deadline-ul si termenul de predare al unei teme si o adauga daca aceasta este valida
         * In caz contrar afiseaza erorile sau mesajele corespunzatoare
         */
        void AdaugaTema()
        {
            Console.WriteLine("Numarul temei: ");
            try
            {
                String id = Console.ReadLine();
                Console.WriteLine("Descriere ");
                String descriere = Console.ReadLine();
                Console.WriteLine("Deadline: ");
                String deadline = Console.ReadLine();
                Console.WriteLine("Predare: ");
                String predare = Console.ReadLine();
                servT.addTema(Int32.Parse(id), descriere, Int32.Parse(deadline), Int32.Parse(predare));
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        /**
         * Citeste de la tastatura id-ul studentului pentru care se adauga nota, id-ul temei la care adaugam nota si nota propriuzisa daca acestea sunt valide
         * In caz contrar afiseaza erorile sau mesajele corespunzatoare
         * Apoi se cere alegerea tipului de penalizare , daca aceasta se aplica sau nu
         */
        void AdaugaNotaPenalizare()
        {
            Console.WriteLine("Dati id-ul studentului pentru care doriti sa adaugati nota:");
            try
            {
                String idS = Console.ReadLine();
                Console.WriteLine("Dati id-ul temei la care doriti sa adaugati nota:");
                String idT = Console.ReadLine();
                Console.WriteLine("Dati nota:");
                String nota = Console.ReadLine();
                while (true)
                {
                    Console.WriteLine("Se i-a in considerare penalizarea ?");
                    String raspuns = Console.ReadLine();
                    Console.WriteLine("FeedBack: ");
                    String feedback = Console.ReadLine();
                    if (raspuns.Equals("da") || raspuns.Equals("DA") || raspuns.Equals("Da"))
                    {
                        servC.addNotaCuPenalizare(Int32.Parse(idS), Int32.Parse(idT), Int32.Parse(nota), feedback);
                        break;
                    }
                    else
                        if (raspuns.Equals("nu") || raspuns.Equals("NU") || raspuns.Equals("Nu"))
                    {
                        servC.addNotaFaraPenalizare(Int32.Parse(idS), Int32.Parse(idT), Int32.Parse(nota), feedback);
                        break;
                    }
                    else
                    {
                        Console.WriteLine("Comanda data este incorecta");
                    }
                }
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }
        void StergeStudent()
        {
            Console.WriteLine("Numarul matricol al studentului pe care doriti sa il stergeti (ID): ");
            try
            {
                String id = Console.ReadLine();
                servS.deleteStudent(Int32.Parse(id));
                //servC.stergeNoteStudent(Int32.Parse(id));
            }

            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);

            }

        }
       
       
        void UpdateStudent()
        {
            Console.WriteLine("Numarul matricol al studentului pe care doriti sa il modificati (ID): ");
            try
            {
                String id = Console.ReadLine();
                Console.WriteLine("Numele nou ");
                String numele = Console.ReadLine();
                Console.WriteLine("Grupa noua: ");
                String grupa = Console.ReadLine();
                Console.WriteLine("Email-ul nou: ");
                String email = Console.ReadLine();
                Console.WriteLine("Profesor nou: ");
                String prof = Console.ReadLine();

                servS.updateStudent(Int32.Parse(id), numele, Int32.Parse(grupa), email, prof);
            }
            
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }

        }
        /**
         * Citeste de la tastatura numarul unei teme, descriera noua , deadline-ul nou si termenul nou de predare si o modifica daca aceasta este valida
         * In caz contrar afiseaza erorile sau mesajele corespunzatoare
         */

        void UpdateTema()
        {
            Console.WriteLine("Numarul temei pe care doriti sa o modificati: ");
            try
            {
                String id = Console.ReadLine();
                Console.WriteLine(servT.findTema(Int32.Parse(id)).ToString());
                Console.WriteLine("Descrierea noua: ");
                String descriere = Console.ReadLine();
                Console.WriteLine("Deadline nou: ");
                String deadline = Console.ReadLine();
                Console.WriteLine("Predarea noua : ");
                String predare = Console.ReadLine();
                servT.updateTema(Int32.Parse(id), descriere, Int32.Parse(deadline), Int32.Parse(predare));
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        /**
         * Afiseaza toate notele unui student din catalog daca studentul exista
         */
       /* void NoteStudent()
        {
            Console.WriteLine("ID: ");
            try
            {
                String id = Console.ReadLine();
                IEnumerable<Inregistrare> it = servC.findNoteStudent(Int32.Parse(id));
                for (Object o : it)
                    Console.WriteLine(o.toString());
            }
           
        }*/

        /**
         * Extinde deadline-ul unei teme existente
         */
        void Prelungire()
        {
            Console.WriteLine("Numarul temei la care doriti sa prelungiti deadline-ul: ");
            try
            {
                String id = Console.ReadLine();
                Console.WriteLine(servT.findTema(Int32.Parse(id)).ToString());
                Tema t = servT.findTema(Int32.Parse(id));
                Console.WriteLine("Deadline nou: ");
                String deadline = Console.ReadLine();
                servT.updateTema(Int32.Parse(id), t.Descriere, Int32.Parse(deadline), t.Predare);
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}
