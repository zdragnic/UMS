class CreateEmployeeDetails < ActiveRecord::Migration[5.1]
  def change
    create_table :employee_details do |t|
      t.belongs_to :user, index: true
      t.float :salary
      t.date :employmentDate


      t.timestamps
    end
  end
end
