class CreateStudentDetails < ActiveRecord::Migration[5.1]
  def change
    create_table :student_details do |t|
      t.belongs_to :user, index: true
      t.integer :budget
      t.date :enrolmentDate
      t.date :disrolmentDate
      t.date :graduationDate

      t.timestamps
    end
  end
end
