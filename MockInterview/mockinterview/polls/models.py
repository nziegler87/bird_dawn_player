from django.db import models


# question
class Interviewer(models.Model):
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)
    coop_company = models.CharField(max_length=200)
    coop_position = models.CharField(max_length=200)
    major = models.CharField(max_length=200)
    email = models.EmailField('Northeastern Email Address')
    date_registered = models.DateField()
    instructor = models.CharField(max_length=200)
    interview_slots = models.IntegerField(default=1)

    def __str__(self):
        return str(self.first_name) + " " + str(self.last_name) + "\n" + str(self.major) + "\n" + \
               str(self.coop_company) + " - " + str(self.coop_position)

    def slots_left(self):
        if self.interview_slots == 0:
            return False
        else:
            return True


# choice
class Interviewee(models.Model):
    Interviewer = models.ForeignKey(Interviewer, on_delete=models.CASCADE)
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)
    major = models.CharField(max_length=200)
    email = models.EmailField('Northeastern Email Address')
    date_registered = models.DateField()
    instructor = models.CharField(max_length=200)
    interview_booked = models.BooleanField(default=False)

    def __str__(self):
        return str(self.first_name) + " " + str(self.last_name) + "\n" + str(self.major)

    def booked_interview(self):
        return self.interview_booked
