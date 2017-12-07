class CreateCourseDepartments < ActiveRecord::Migration[5.1]
  def change
    create_table :course_departments do |t|
      t.references :course, foreign_key: true
      t.references :department, foreign_key: true
      t.references :academic_year, foreign_key: true
      t.references :semester, foreign_key: true

      t.timestamps
    end
  end
end
