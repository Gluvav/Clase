# -*- coding: utf-8 -*-

from odoo import models, fields, api


class libro1(models.Model):
    _name = 'modulo1.libro1'
    _description = 'modulo1.libro1'

    name = fields.Char()
    value = fields.Integer()
    value2 = fields.Float(compute="_value_pc", store=True)
    description = fields.Text()
