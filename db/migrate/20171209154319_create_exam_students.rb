class CreateExamStudents < ActiveRecord::Migration[5.1]
  def change
    create_table :exam_students do |t|
      t.references :exam, foreign_key: true
      t.references :user, foreign_key: true
      t.bit :status

      t.timestamps
    end
  end
end
