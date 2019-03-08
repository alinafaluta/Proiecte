using Laborator12_13.domain;
using Laborator12_13.Domain;
using Laborator12_13.Validator;
using Laborator12_13;

using System;
using System.Collections.Generic;
using System.Text;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator12_13.Repository
{
    class StudentInFileRepository:InFileRepository<int,Student>
    {
        public StudentInFileRepository(IValidator<Student> vali, string fileName) : base(vali, fileName, EntityToFileMapping.CreateStudent)
        {
            loadFromFile();
        }

    }
}
