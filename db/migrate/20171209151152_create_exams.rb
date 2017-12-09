class CreateExams < ActiveRecord::Migration[5.1]
  def change
    create_table :exams do |t|
      t.string :title
      t.date :scheduled
      t.references :course_department, foreign_key: true

      t.timestamps
    end
  end
end
