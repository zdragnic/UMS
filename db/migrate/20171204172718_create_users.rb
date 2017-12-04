class CreateUsers < ActiveRecord::Migration[5.1]
  def change
    create_table :users do |t|
      t.belongs_to :role, index: true
      t.string :username
      t.string :password
      t.string :name
      t.string :lastname
      t.date :birthDate
      t.string :address
      t.string :birthplace

      t.timestamps
    end
  end
end
