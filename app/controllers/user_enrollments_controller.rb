class UserEnrollmentsController < ApplicationController
  before_action :set_user_enrollment, only: [:show, :edit, :update, :destroy]

  # GET /user_enrollments
  # GET /user_enrollments.json
  def index
    @user_enrollments = UserEnrollment.all
  end

  # GET /user_enrollments/1
  # GET /user_enrollments/1.json
  def show
  end

  # GET /user_enrollments/new
  def new
    @user_enrollment = UserEnrollment.new
    @course_id = params[:course_department_id] if params[:course_department_id]
    puts "ISPIS"
    puts params[:course_department_id]
  end

  # GET /user_enrollments/1/edit
  def edit
  end

  # POST /user_enrollments
  # POST /user_enrollments.json
  def create
    @user_enrollment = UserEnrollment.new(user_enrollment_params)

    respond_to do |format|
      if @user_enrollment.save
        format.html { redirect_to @user_enrollment, notice: 'User enrollment was successfully created.' }
        format.json { render :show, status: :created, location: @user_enrollment }
      else
        format.html { render :new }
        format.json { render json: @user_enrollment.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /user_enrollments/1
  # PATCH/PUT /user_enrollments/1.json
  def update
    respond_to do |format|
      if @user_enrollment.update(user_enrollment_params)
        format.html { redirect_to @user_enrollment, notice: 'User enrollment was successfully updated.' }
        format.json { render :show, status: :ok, location: @user_enrollment }
      else
        format.html { render :edit }
        format.json { render json: @user_enrollment.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /user_enrollments/1
  # DELETE /user_enrollments/1.json
  def destroy
    @user_enrollment.destroy
    respond_to do |format|
      format.html { redirect_to user_enrollments_url, notice: 'User enrollment was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user_enrollment
      @user_enrollment = UserEnrollment.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def user_enrollment_params
      params.require(:user_enrollment).permit(:study_year_id, :user_id, :course_department_id, :lab_group_id)
    end
end
