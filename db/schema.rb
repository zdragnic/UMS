# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20171207150732) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "academic_years", force: :cascade do |t|
    t.string "title"
    t.bit "active", limit: 1
    t.date "start"
    t.date "end"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "course_departments", force: :cascade do |t|
    t.bigint "course_id"
    t.bigint "department_id"
    t.bigint "academic_year_id"
    t.bigint "semester_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["academic_year_id"], name: "index_course_departments_on_academic_year_id"
    t.index ["course_id"], name: "index_course_departments_on_course_id"
    t.index ["department_id"], name: "index_course_departments_on_department_id"
    t.index ["semester_id"], name: "index_course_departments_on_semester_id"
  end

  create_table "courses", force: :cascade do |t|
    t.string "title"
    t.string "code"
    t.bigint "user_id"
    t.integer "responsible"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_courses_on_user_id"
  end

  create_table "departments", force: :cascade do |t|
    t.string "title"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "employee_details", force: :cascade do |t|
    t.bigint "user_id"
    t.float "salary"
    t.date "employmentDate"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_employee_details_on_user_id"
  end

  create_table "lab_groups", force: :cascade do |t|
    t.string "title"
    t.bigint "course_department_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["course_department_id"], name: "index_lab_groups_on_course_department_id"
  end

  create_table "roles", force: :cascade do |t|
    t.string "name"
    t.string "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "semesters", force: :cascade do |t|
    t.string "title"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "student_details", force: :cascade do |t|
    t.bigint "user_id"
    t.string "indeks"
    t.bit "budget", limit: 1
    t.date "enrollmentDate"
    t.date "disrollmentDate"
    t.date "graduationDate"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_student_details_on_user_id"
  end

  create_table "study_years", force: :cascade do |t|
    t.string "title"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "user_enrollments", force: :cascade do |t|
    t.bigint "study_year_id"
    t.bigint "user_id"
    t.bigint "course_department_id"
    t.bigint "lab_group_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["course_department_id"], name: "index_user_enrollments_on_course_department_id"
    t.index ["lab_group_id"], name: "index_user_enrollments_on_lab_group_id"
    t.index ["study_year_id"], name: "index_user_enrollments_on_study_year_id"
    t.index ["user_id"], name: "index_user_enrollments_on_user_id"
  end

  create_table "users", force: :cascade do |t|
    t.bigint "role_id"
    t.string "username"
    t.string "password"
    t.string "name"
    t.string "lastname"
    t.date "birthDate"
    t.string "address"
    t.string "birthplace"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["role_id"], name: "index_users_on_role_id"
  end

  add_foreign_key "course_departments", "academic_years"
  add_foreign_key "course_departments", "courses"
  add_foreign_key "course_departments", "departments"
  add_foreign_key "course_departments", "semesters"
  add_foreign_key "courses", "users"
  add_foreign_key "lab_groups", "course_departments"
  add_foreign_key "student_details", "users"
  add_foreign_key "user_enrollments", "course_departments"
  add_foreign_key "user_enrollments", "lab_groups"
  add_foreign_key "user_enrollments", "study_years"
  add_foreign_key "user_enrollments", "users"
end
