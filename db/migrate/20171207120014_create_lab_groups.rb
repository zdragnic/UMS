class CreateLabGroups < ActiveRecord::Migration[5.1]
  def change
    create_table :lab_groups do |t|
      t.string :title
      t.references :course_department, foreign_key: true

      t.timestamps
    end
  end
end
