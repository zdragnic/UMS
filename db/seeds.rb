
#roles = Role.create([{ name: 'admin', description: 'Upravlja korisnickim racunima.' }, { name: 'student', description: 'Pristupa podstranici za studente.' }, { name: 'studentska', description: '' }, { name: 'akademsko', description: 'Akademsko osoblje.' }])
# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)


#courses = Course.create([{ title: 'C++', code: 'ETFRI05', user_id: 4 }, { title: 'Databases', code: 'ETFRI06', user_id: 4 }])
#users= User.create([{ username: 'vildana', password: '123', name: 'Vildana', lastname: 'Panjeta', birthDate: Date.today, address: 'Humska 32', birthplace:'Sarajevo', role_id:1 }, { username: 'zerina', password: '123', name: 'Zerina', lastname: 'Dragnic', birthDate: Date.today, address: 'Avde Hume 32', birthplace:'Sarajevo', role_id:2 }, { username: 'amina', password: '123', name: 'Amina', lastname: 'Puce', birthDate: Date.today, address: 'Vakufska 32', birthplace:'Koblenz', role_id:3 }, { username: 'emir', password: '123', name: 'Emir', lastname: 'Buza', birthDate: Date.today, address: 'Zmaja od Bosne', birthplace:'Sarajevo', role_id:4 }])
#semesters = Semester.create([{title: 'prvi'}, {title: 'drugi'}, {title: 'treci'},{title: 'cetvrti'}, {title: 'peti'},{title: 'sesti'} ])
#AcademicYear.create([{title:'2017/2018', active:1, start:Date.new(2017,9,1), end:Date.new(2018,7,10)}, {title:'2016/2017', active:0, start:Date.new(2016,9,1), end:Date.new(2017,7,10)}])
CourseDepartment.create([{course_id:1,department_id:1,academic_year_id:1,semester_id:1}, {course_id:2,department_id:1,academic_year_id:1,semester_id:1}, {course_id:4,department_id:1,academic_year_id:1,semester_id:1}, {course_id:5,department_id:1,academic_year_id:1,semester_id:1} ])