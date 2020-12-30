from django.contrib.auth.forms import UserCreationForm, UserChangeForm

from .models import CustomUser


class CustomUserCreationForm(UserCreationForm):

    class Meta(UserCreationForm):
        model = CustomUser
        fields = ('email', 'password', 
        'first_name', 'last_name',
        'user_type', 'major', 'max_interview_slots',
        'coop_company', 'coop_position',
        'instructor', 'linked_persons',)


class CustomUserChangeForm(UserChangeForm):

    class Meta:
        model = CustomUser
        fields = ('email', 'password', 
        'first_name', 'last_name',
        'user_type', 'major', 'max_interview_slots',
        'coop_company', 'coop_position',
        'instructor', 'linked_persons',)