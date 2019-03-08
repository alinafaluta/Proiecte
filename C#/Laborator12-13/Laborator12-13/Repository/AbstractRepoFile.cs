using Laborator12_13.domain;
using Laborator12_13.repository;
using Laborator12_13.Validator;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
/*
namespace Laborator12_13.Repository
{
    class AbstractRepoFile<ID, TE> : InMemoryRepository<ID, TE> where TE : IHasID<ID>
    {
        
            protected String fileName;

            protected AbstractRepoFile(String fileName, IValidator<TE> val) : base(val)
            {
                this.fileName = fileName;
                LoadFromFile();
            }

     
            public void LoadFromFile()
            {
                try
                {
                    var fileStream = new FileStream(fileName, FileMode.Open, FileAccess.Read);
                    using (var streamReader = new StreamReader(fileStream))
                    {
                        string line;
                        while ((line = streamReader.ReadLine()) != null)
                        {
                            if (line == "") throw new ValidationException("Empty file.");
                            TE temp = ExtractEntity(line);
                            Save(temp);
                            // process the line
                        }
                    }
                }
                catch (ValidationException e)
                {
                Console.WriteLine(e.Message);
            }
            }


            public override TE save(TE entity)
            {
                TE returnedEntity = base.save(entity);
                if (returnedEntity == null)
                {
                    WriteToFile();
                }
                return returnedEntity;
            }

            public override TE update(TE entity)
            {
                TE enti = base.update(entity);
                WriteToFile();
                return enti;
            }

            public void WriteToFile()
            {
                using (StreamWriter sw = new StreamWriter(fileName))
                {
                    //TODO:
                    findAll().ToList().ForEach(entity => sw.WriteLine(StringFromEntity(entity)));
                    sw.Write("\n");
                }
            }

            public abstract String StringFromEntity(TE entity);

            public abstract TE ExtractEntity(string entity);
        }
    }
*/
