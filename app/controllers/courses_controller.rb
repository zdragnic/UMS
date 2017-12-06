class CoursesController < ApplicationController
  before_action :set_course, only: [:show, :edit, :update, :destroy]



  # GET /courses
  # GET /courses.json
  def index
    user = User.find(params[:user_id])
    #2nd you get all the comments of this post
    @courses = user.courses

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @courses }
    end
  end

  # GET /courses/1
  # GET /courses/1.json
  def show
    user = User.find(params[:user_id])
    #2nd you retrieve the comment thanks to params[:id]
    @course = user.courses.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @course }
    end
  end

  # GET /courses/new
  def new
    user = User.find(params[:user_id])
    #2nd you build a new one
    @course = user.courses.build

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @course }
    end
  end

  # GET /courses/1/edit
  def edit
    user= User.find(params[:user_id])
    #2nd you retrieve the comment thanks to params[:id]
    @course = user.courses.find(params[:id])
  end


  # POST /courses
  # POST /courses.json
  def create
    user = User.find(params[:user_id])
    #2nd you create the comment with arguments in params[:comment]
    @course = user.courses.create(course_params)

    respond_to do |format|
      if @course.save
        #1st argument of redirect_to is an array, in order to build the correct route to the nested resource comment
        format.html { redirect_to([@course.user, @course], :notice => 'Course was successfully created.') }
        #the key :location is associated to an array in order to build the correct route to the nested resource comment
        format.xml  { render :xml => @course, :status => :created, :location => [@course.user, @course] }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @course.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /courses/1
  # PATCH/PUT /courses/1.json
  def update
    course = User.find(params[:user_id])
    #2nd you retrieve the comment thanks to params[:id]
    @course = user.courses.find(params[:course_params])

    respond_to do |format|
      if @course.update_attributes(params[:course])
        #1st argument of redirect_to is an array, in order to build the correct route to the nested resource comment
        format.html { redirect_to([@course.user, @course], :notice => 'Comment was successfully updated.') }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @comment.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /courses/1
  # DELETE /courses/1.json
  def destroy
    @course.destroy
    respond_to do |format|
      format.html { redirect_to courses_url, notice: 'Course was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
  # Use callbacks to share common setup or constraints between actions.
  def set_course
    @course = Course.find(params[:id])
  end

  # Never trust parameters from the scary internet, only allow the white list through.
  def course_params
    params.require(:course).permit(:title, :code, :user_id, :responsible)
  end
end