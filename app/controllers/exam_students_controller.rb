class ExamStudentsController < ApplicationController
  before_action :set_exam_student, only: [:show, :edit, :update, :destroy]

  # GET /exam_students
  # GET /exam_students.json
  def index
    @exam_students = ExamStudent.all
  end

  # GET /exam_students/1
  # GET /exam_students/1.json
  def show
  end

  # GET /exam_students/new
  def new
    @exam_student = ExamStudent.new
    if params[:user_id]
      @exam_student.user_id = params[:user_id]
    end
    if params[:exam_id]
      @exam_student.exam_id = params[:exam_id]
    end
    if params[:status]
      @exam_student.status = params[:status]
    end

  end

  # GET /exam_students/1/edit
  def edit
  end

  # POST /exam_students
  # POST /exam_students.json
  def create
    @exam_student = ExamStudent.new
    if params[:user_id]
      @exam_student.user_id = params[:user_id]
    end
    if params[:exam_id]
      @exam_student.exam_id = params[:exam_id]
    end
    if params[:status]
      @exam_student.status = params[:status]
    end

    respond_to do |format|
      if @exam_student.save
        format.html { redirect_to exams_url, notice: 'Prijavili ste ispit.' }
        format.json { render :show, status: :created, location: @exam_student }
      else
        format.html { render :new }
        format.json { render json: @exam_student.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /exam_students/1
  # PATCH/PUT /exam_students/1.json
  def update
    respond_to do |format|
      if @exam_student.update(exam_student_params)
        format.html { redirect_to @exam_student, notice: 'Exam student was successfully updated.' }
        format.json { render :show, status: :ok, location: @exam_student }
      else
        format.html { render :edit }
        format.json { render json: @exam_student.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /exam_students/1
  # DELETE /exam_students/1.json
  def destroy
    @exam_student.destroy
    respond_to do |format|
      format.html { redirect_to exams_url, notice: 'Odjavili ste ispit.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_exam_student
      @exam_student = ExamStudent.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
  def exam_student_params
    params.require(:exam_student).permit(:exam_id, :user_id, :status)
  end
end
