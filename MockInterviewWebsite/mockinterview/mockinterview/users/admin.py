from django.contrib import admin
from django.contrib.auth import admin as auth_admin
from django.contrib.auth import get_user_model

from mockinterview.users.forms import (
    UserChangeForm,
    UserCreationForm,
)

User = get_user_model()


@admin.register(User)
class UserAdmin(auth_admin.UserAdmin):

    form = UserChangeForm
    add_form = UserCreationForm
    fieldsets = (
        ("User", {"fields": ("name",'user_type', 'major', 'max_interview_slots', 'coop_company', 'coop_position', 'instructor', 'linked_interviewees')}),
    ) + auth_admin.UserAdmin.fieldsets
    list_display = ["username", "name", "is_superuser", 'user_type']
    search_fields = ["name"]
