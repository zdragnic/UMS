class AddResponsibleIdToCourses < ActiveRecord::Migration[5.1]
  def change
    add_column :courses, :responsible, :integer
  end
end
