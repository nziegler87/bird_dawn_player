from django.shortcuts import render
from django.views.generic import TemplateView
from user.models import CustomUser
from django.contrib.auth.mixins import LoginRequiredMixin

class HomepageView(LoginRequiredMixin, TemplateView):
    template_name = 'index.html'

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['my_statement'] = 'Nice to see you!'
        context['users'] = CustomUser.objects.all()
        return context
