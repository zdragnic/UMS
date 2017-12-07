class CreateStudyYears < ActiveRecord::Migration[5.1]
  def change
    create_table :study_years do |t|
      t.string :title

      t.timestamps
    end
  end
end
