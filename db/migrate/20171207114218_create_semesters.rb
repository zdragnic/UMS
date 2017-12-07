class CreateSemesters < ActiveRecord::Migration[5.1]
  def change
    create_table :semesters do |t|
      t.string :title

      t.timestamps
    end
  end
end
