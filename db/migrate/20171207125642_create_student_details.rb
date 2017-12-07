class CreateStudentDetails < ActiveRecord::Migration[5.1]
  def change
    create_table :student_details do |t|
      t.references :user, foreign_key: true
      t.string :indeks
      t.bit :budget
      t.date :enrollmentDate
      t.date :disrollmentDate
      t.date :graduationDate

      t.timestamps
    end
  end
end
