from django.db import models
from django.contrib.auth.models import AbstractUser

class CustomUser(AbstractUser):

    class UserType(models.TextChoices):
        INTERVIEWER = 'interviewer', 'Interviewer'
        INTERVIEWEE = 'interviewee', 'Interviewee'
        UNSPECIFIED = 'unspecified', 'Unspecified'
    
    class MajorType(models.TextChoices):
        BSME = 'BSME', 'Mechanical Engineering'
        BSIE = 'BSIE', 'Industrial Engineering'
        UNSPECIFIED = 'unspecified', 'Unspecified'
    
    class InstructorNames(models.TextChoices):
        # AHERN = 'ahern', 'Mike Ahern'
        # BLEAKNEY = 'bleakney', 'John Bleakney'
        # CARDILLO = 'cardillo', 'Pete Cardillo'
        CARPENTER_OLIVERIA = 'carpenter-oliveria', 'Heather Carpenter-Oliveria'
        # HALS = 'hals', 'Kristina Hals'
        LOONEY = 'looney', 'Georgia Looney'
        LU = 'lu', 'Lucy Lu'
        MACILROY = 'macilroy', 'Robin MacIlroy'
        MOCKLER = 'mockler', 'Sarah Mockler'
        # NOGUEIRA = 'nogueira', 'Alison Nogueira'
        PARKER = 'parker', 'Kevin Parker'
        ZIEGLER = 'ziegler', 'Nathanial Ziegler'
        UNSPECIFIED = 'unspecified', 'Unspecified'
    
    name = models.CharField("Name of User", blank=True, max_length=255)

    user_type = models.CharField('Interviewee/Interviewer', max_length=15, 
        choices=UserType.choices, default=UserType.UNSPECIFIED)
    
    major = models.CharField('Major', max_length=20,
        choices=MajorType.choices, default=MajorType.UNSPECIFIED)
    
    max_interview_slots = models.IntegerField('Interview Slots', choices=[(1,"1"),(2,"2")], default=1)

    coop_company = models.CharField('First Co-op Company Name', blank=True, max_length=250)
    
    coop_position = models.CharField('First Co-op Position Name', blank=True, max_length=250)
    
    instructor = models.CharField('Instructor Name', max_length = 20,
        choices=InstructorNames.choices, default=InstructorNames.UNSPECIFIED)
    
    # Need to see if this is correct. Should be able to create a user with this being blank.
    linked_persons = models.ManyToManyField('CustomUser', blank=True)

    def __str__(self):
        return self.first_name + " " + self.last_name + " (" + self.email + ")"
    
    def is_booked(self):
        count = 0
        for person in self.linked_persons.all():
            count += 1
        return self.max_interview_slots < count
    