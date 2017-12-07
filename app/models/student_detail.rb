class StudentDetail < ApplicationRecord
  belongs_to :user

  def self.search(term)
    if term
      where('indeks LIKE ?', "%#{term}%")
    else
      all
    end
  end
end
