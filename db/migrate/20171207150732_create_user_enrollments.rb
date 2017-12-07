class CreateUserEnrollments < ActiveRecord::Migration[5.1]
  def change
    create_table :user_enrollments do |t|
      t.references :study_year, foreign_key: true
      t.references :user, foreign_key: true
      t.references :course_department, foreign_key: true
      t.references :lab_group, foreign_key: true

      t.timestamps
    end
  end
end
