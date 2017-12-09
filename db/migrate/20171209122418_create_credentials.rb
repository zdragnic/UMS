class CreateCredentials < ActiveRecord::Migration[5.1]
  def change
    create_table :credentials do |t|
      t.string :title

      t.timestamps
    end
  end
end
