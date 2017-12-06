class CreateCourses < ActiveRecord::Migration[5.1]
  def change
    create_table :courses do |t|
      t.string :title
      t.string :code
      t.references :user, foreign_key: true
      t.integer :responsible

      t.timestamps
    end
  end
end
