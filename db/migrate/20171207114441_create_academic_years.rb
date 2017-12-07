class CreateAcademicYears < ActiveRecord::Migration[5.1]
  def change
    create_table :academic_years do |t|
      t.string :title
      t.bit :active
      t.date :start
      t.date :end

      t.timestamps
    end
  end
end
