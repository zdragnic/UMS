class CreateCourses < ActiveRecord::Migration[5.1]
  def change
    create_table :courses do |t|
      t.belongs_to :user, index: true
      t.string :title
      t.string :code

      t.timestamps
    end
    add_foreign_key :courses, :users
  end
end
